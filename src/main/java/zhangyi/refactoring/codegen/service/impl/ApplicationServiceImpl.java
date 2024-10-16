package zhangyi.refactoring.codegen.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import zhangyi.refactoring.codegen.metadata.client.MetadataClient;
import zhangyi.refactoring.codegen.metadata.dto.*;
import zhangyi.refactoring.codegen.metadata.entity.*;
import zhangyi.refactoring.codegen.service.AbstractApplicationService;
import zhangyi.refactoring.codegen.service.SqlHelper;
import zhangyi.refactoring.codegen.utility.Strings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AllArgsConstructor
@Slf4j
public class ApplicationServiceImpl extends AbstractApplicationService {
    protected final Configuration configuration;
    protected final MetadataClient metadataClient;

    public static final ThreadLocal<Path> TEMP_PROJECT_ROOT_PATH = new ThreadLocal<>();

    protected static final Path BASE_RESOURCE_PATH = Paths.get("src", "main", "resources");
    protected static final Path BASE_JAVA_PATH = Paths.get("src", "main", "java");
    protected static final Path BASE_TEST_PATH = Paths.get("src", "test", "java");
    private SqlHelper sqlHelper;
    private final ProjectFileWriter projectFileWriter;

    private final static Map<String, String> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put("string", "VARCHAR(255)");
        TYPE_MAP.put("integer", "int");
        TYPE_MAP.put("long", "bigint");
    }

    @Override
    public String generateParentProject(ApplicationMetadata applicationMetadata) {
        Path tempProjectPath = TEMP_PROJECT_ROOT_PATH.get();
        if (Objects.isNull(tempProjectPath)) {
            tempProjectPath = createTempProjectPath();
        }
        Path projectPath = tempProjectPath.resolve(applicationMetadata.getProject().getArtifact());
        notExistThenCreate(projectPath);
        String fileName = projectPath.resolve("pom.xml").toString();
        return projectFileWriter.write(applicationMetadata, fileName);
    }

    @Override
    public Path generateParentProject(ApplicationMetadataDTO applicationMetadata) {
        Path tempProjectRootDirectory = createTempProjectPath();
        Path projectPath = tempProjectRootDirectory.resolve(applicationMetadata.getProject().getArtifactId());
        notExistThenCreate(projectPath);
        String fileName = projectPath.resolve("pom.xml").toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("parent-pom.ftl").process(applicationMetadata, writer);
            //复制.mvn目录和mvnw、mvnw.cmd
            copyMavenFiles2Project(projectPath);
            return projectPath;
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    private static Path createTempProjectPath() {
        //创建临时目录
        Path tempProjectRootDirectory;
        try {
            tempProjectRootDirectory = Files.createTempDirectory("project-");
            TEMP_PROJECT_ROOT_PATH.set(tempProjectRootDirectory);
        } catch (IOException e) {
            log.error("创建临时项目根目录失败！", e);
            throw new RuntimeException(e);
        }
        return tempProjectRootDirectory;
    }

    public void copyMavenFiles2Project(Path projectPath) {
        ClassPathResource r1 = new ClassPathResource("static/maven/mvnw");
        ClassPathResource r2 = new ClassPathResource("static/maven/mvnw.cmd");
        ClassPathResource r3 = new ClassPathResource("static/maven/.mvn/wrapper/maven-wrapper.jar");
        ClassPathResource r4 = new ClassPathResource("static/maven/.mvn/wrapper/maven-wrapper" +
                ".properties");
        try (InputStream is1 = r1.getInputStream(); InputStream is2 = r2.getInputStream(); InputStream is3 =
                r3.getInputStream(); InputStream is4 = r4.getInputStream();) {
            Files.copy(is1, projectPath.resolve(r1.getFilename()));
            Files.copy(is2, projectPath.resolve(r2.getFilename()));
            Path wrapper = projectPath.resolve(".mvn").resolve("wrapper");
            notExistThenCreate(wrapper);
            Files.copy(is3, wrapper.resolve(r3.getFilename()));
            Files.copy(is4, wrapper.resolve(r4.getFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateParentProject(Long id) {
        return generateParentProject(metadataClient.getApplicationMetadata(id)).toString();
    }

    @Override
    public String generateChildrenProject(@NotNull ModuleMetadata moduleMetadata) {
        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifact());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifact());
        notExistThenCreate(projectPath);
        String fileName = projectPath.resolve("pom.xml").toString();
        try (Writer writer = new FileWriter(fileName)) {
            Template temp = configuration.getTemplate("children-pom.ftl");
            temp.process(moduleMetadata, writer);
            //修改父项目pom，增加<module></module>
            parentProjectAddModule(parentProjectPath.resolve("pom.xml").toFile(), moduleMetadata.getArtifact());
            generateSrc(projectPath, moduleMetadata.getPackageName());
            return fileName;
        } catch (IOException e) {
            log.error("获取 项目pom文件出错！{}", e);
            throw new RuntimeException("获取 项目pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成项目pom文件出错！{}", e);
            throw new RuntimeException("生成项目pom文件出错！");
        }
    }

    @Override
    public void generateChildrenProject(Long id) {
        List<ModuleMetadataDTO> moduleMetadataList =
                metadataClient.getModuleMetadata(id);
        moduleMetadataList.forEach(this::generateChildrenProject);
    }

    @Override
    public void generateEntity(Long applicationId) {
        ApplicationMetadataDTO application = metadataClient.getApplicationMetadata(applicationId);
        List<ModuleMetadataDTO> modules = application.getModules();
        modules.forEach(item -> generateEntities(item, item.getDomains()));
    }

    private void generateEntity(ModuleMetadataDTO moduleMetadata,
                                DomainMetadataDTO domainMetadata) {
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("domain", domainMetadata);
        metaData.put("package", moduleMetadata.getPackageName() + ".entity");

        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);
        Path entityPath = projectPath.resolve(BASE_JAVA_PATH)
                .resolve(Paths.get("", moduleMetadata.getPackageName().split("\\.")))
                .resolve("entity");
        notExistThenCreate(entityPath);
        String fileName =
                entityPath.resolve(String.format("%s.java", Strings.capFirst(domainMetadata.getNameEnglish()))).toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("entity.ftl").process(metaData, writer);
            log.info("生成文件：{}", fileName);
        } catch (IOException e) {
            log.error("获取文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    protected void generateEntities(ModuleMetadataDTO moduleMetadata, List<DomainMetadataDTO> domainMetadata) {
        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);
        Path entityPath = projectPath.resolve(BASE_JAVA_PATH)
                .resolve(Paths.get("", moduleMetadata.getPackageName().split("\\.")))
                .resolve("entity");
        notExistThenCreate(entityPath);
        for (DomainMetadataDTO domain : domainMetadata) {
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("domain", domain);
            metaData.put("package", moduleMetadata.getPackageName() + ".entity");

            String fileName = entityPath.resolve(String.format("%s.java",
                    Strings.capFirst(domain.getNameEnglish()))).toString();
            try (Writer writer = new FileWriter(fileName)) {
                configuration.getTemplate("entity.ftl").process(metaData, writer);
                log.info("生成文件：{}", fileName);
            } catch (IOException e) {
                log.error("获取文件出错！{}", e);
                throw new RuntimeException("获取 应用父工程pom文件出错！");
            } catch (TemplateException e) {
                log.error("生成文件出错！{}", e);
                throw new RuntimeException("生成应用父工程pom文件出错！");
            }
        }
    }

    protected void generateChildrenProject(ModuleMetadataDTO moduleMetadata) {
        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);
        String fileName = projectPath.resolve("pom.xml").toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("children-pom.ftl").process(moduleMetadata, writer);
            generateSrc(projectPath, moduleMetadata.getPackageName());
        } catch (IOException e) {
            log.error("获取 项目pom文件出错！{}", e);
            throw new RuntimeException("获取 项目pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成项目pom文件出错！{}", e);
            throw new RuntimeException("生成项目pom文件出错！");
        }
        super.generateChildrenProject(moduleMetadata);
    }

    @Override
    public void generateController(@NotNull Long id) {
        List<ModuleMetadataDTO> modules = metadataClient.getModuleMetadata(id);
        for (ModuleMetadataDTO moduleMetadata : modules) {
            List<ServiceMetadataDTO> services = moduleMetadata.getServices();
            for (ServiceMetadataDTO serviceMetadata : services) {
                generateController(moduleMetadata, serviceMetadata);
            }
        }
    }

    protected void generateController(ModuleMetadataDTO moduleMetadata, ServiceMetadataDTO serviceMetadata) {
        List<FunctionMetadataDTO> functionMetadata = serviceMetadata.getFunctions();
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("ServiceMetadata", serviceMetadata);
        metaData.put("functions", functionMetadata);
        List<String> importNameList = new ArrayList<>();
        String name = serviceMetadata.getName();
        String capFirstName = Strings.capFirst(name);
        importNameList.add(moduleMetadata.getPackageName() + ".service." + capFirstName + "Service");
        metaData.put("imports", importNameList);
        metaData.put("package", moduleMetadata.getPackageName() + ".controller");

        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);
        Path controllerPath = projectPath.resolve(BASE_JAVA_PATH)
                .resolve(Paths.get("", moduleMetadata.getPackageName().split("\\.")))
                .resolve("controller");
        notExistThenCreate(controllerPath);
        String fileName = controllerPath
                .resolve(String.format("%sController.java", capFirstName)).toString();

        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("controller.ftl").process(metaData, writer);
            log.info("生成文件：{}", fileName);
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    @Override
    public void generateService(@NotNull Long id) {
        List<ModuleMetadataDTO> moduleMetadataList =
                metadataClient.getModuleMetadata(id);
        for (ModuleMetadataDTO moduleMetadata : moduleMetadataList) {
            List<ServiceMetadataDTO> services = moduleMetadata.getServices();
            for (ServiceMetadataDTO serviceMetadata : services) {
                generateService(moduleMetadata, serviceMetadata);
            }
        }
    }

    protected void generateService(ModuleMetadataDTO moduleMetadata, ServiceMetadataDTO serviceMetadata) {
        List<DomainMetadataDTO> domainMetadataList = moduleMetadata.getDomains();
        Map<String, Object> interfaceMetaData = new HashMap<>();
        interfaceMetaData.put("serviceName", serviceMetadata.getNameEnglish());
        interfaceMetaData.put("package", moduleMetadata.getPackageName() + ".service");

        Map<String, Object> metaData = new HashMap<>();
        metaData.put("ServiceMetadata", serviceMetadata);
        metaData.put("domainList", domainMetadataList);
        List<String> importNameList = new ArrayList<>();
        String name = serviceMetadata.getNameEnglish();
        String capFirstName = Strings.capFirst(name);
        importNameList.add(moduleMetadata.getPackageName() + ".service." + capFirstName + "Service");

        for (DomainMetadataDTO domainDTO : domainMetadataList) {
            String domainName = domainDTO.getNameEnglish();
            String capFirstDomainName = Strings.capFirst(domainName);
            importNameList.add(moduleMetadata.getPackageName() + ".mapper." + capFirstDomainName + "Mapper");
        }

        metaData.put("imports", importNameList);
        metaData.put("package", moduleMetadata.getPackageName() + ".service.impl");
        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);
        Path servicePath = projectPath.resolve(BASE_JAVA_PATH)
                .resolve(Paths.get("", moduleMetadata.getPackageName().split("\\."))).resolve("service");
        String serviceInterfaceFileName = servicePath.resolve(String.format("%sService.java",
                capFirstName)).toString();
        Path implPath = servicePath.resolve("impl");
        notExistThenCreate(implPath);
        String serviceImplFileName = implPath.resolve(String.format("%sServiceImpl.java", capFirstName)).toString();
        try (Writer interfaceWriter = new FileWriter(serviceInterfaceFileName);
             Writer implWriter = new FileWriter(serviceImplFileName)) {
            configuration.getTemplate("service-interface.ftl").process(interfaceMetaData, interfaceWriter);
            configuration.getTemplate("service-impl.ftl").process(metaData, implWriter);
            log.info("生成文件：{}", serviceInterfaceFileName);
            log.info("生成文件：{}", serviceImplFileName);
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    @Override
    public void generateMapper(@NotNull Long id) {
        List<ModuleMetadataDTO> moduleMetadataList = metadataClient.getModuleMetadata(id);
        for (ModuleMetadataDTO moduleMetadata : moduleMetadataList) {
            generateModuleMappers(moduleMetadata);
        }
    }

    protected void generateModuleMappers(ModuleMetadataDTO moduleMetadata) {
        generateMappersInModule(moduleMetadata, moduleMetadata.getDomains());
        generateMapperXMLsInModule(moduleMetadata, moduleMetadata.getDomains());
    }

    protected void generateMapper(ModuleMetadataDTO moduleMetadata, DomainMetadataDTO domainMetadata) {
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("mapperName", domainMetadata.getNameEnglish());
        List<String> importNameList = new ArrayList<>();
        String domainName = domainMetadata.getNameEnglish();
        String capFirstDomainName = Strings.capFirst(domainName);
        importNameList.add(moduleMetadata.getPackageName() + ".entity." + capFirstDomainName);
        metaData.put("imports", importNameList);
        metaData.put("package", moduleMetadata.getPackageName() + ".mapper");

        Path javaPath = getJavaPath(moduleMetadata);
        Path mapperJavaPath = javaPath.resolve("mapper");
        notExistThenCreate(mapperJavaPath);
        String fileName = mapperJavaPath.resolve(String.format("%sMapper.java", capFirstDomainName)).toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("mapper.ftl").process(metaData, writer);
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    private void generateMappersInModule(ModuleMetadataDTO moduleMetadata, List<DomainMetadataDTO> domainList) {
        for (DomainMetadataDTO domain : domainList) {
            generateMapper(moduleMetadata, domain);
        }
    }

    protected void generateMapperXML(ModuleMetadataDTO moduleMetadata, DomainMetadataDTO domainMetadata) {
        String capFirstDomainName = Strings.capFirst(domainMetadata.getNameEnglish());
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("mapperReference",
                moduleMetadata.getPackageName() + ".mapper." + capFirstDomainName + "Mapper");
        metaData.put("domainReference",
                moduleMetadata.getPackageName() + ".entity." + capFirstDomainName);

        metaData.put("domainProperties", domainMetadata.getProperties());

        Path resourcePath = getResourcePath(moduleMetadata);
        Path mapperXmlPath = resourcePath.resolve("mapper");
        notExistThenCreate(mapperXmlPath);
        String fileName = mapperXmlPath.resolve(String.format("%sMapper.xml", capFirstDomainName)).toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("mapper-xml.ftl").process(metaData, writer);
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    protected void generateMapperXMLsInModule(ModuleMetadataDTO moduleMetadata, List<DomainMetadataDTO> domainList) {
        for (DomainMetadataDTO domain : domainList) {
            generateMapperXML(moduleMetadata, domain);
        }
    }

    protected static Path getJavaPath(ModuleMetadataDTO moduleMetadata) {
        Path projectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path modulePath = projectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(modulePath);

        Path basePackage = Paths.get("", moduleMetadata.getPackageName().split("\\."));
        return modulePath.resolve(BASE_JAVA_PATH).resolve(basePackage);
    }

    @Override
    public void generateSpringBootStartupClass(Long id) {
        ApplicationMetadataDTO applicationMetadata = metadataClient.getApplicationMetadata(id);
        List<ModuleMetadataDTO> moduleMetadataList = applicationMetadata.getModules();
        for (ModuleMetadataDTO moduleMetadata : moduleMetadataList) {
            generateSpringbootStartupClass(moduleMetadata);
        }
    }

    @Override
    public void generateConfigFile(Long id) {
        List<ModuleMetadataDTO> moduleMetadataList =
                metadataClient.getModuleMetadata(id);
        for (ModuleMetadataDTO moduleMetadata : moduleMetadataList) {
            generateConfigfile(moduleMetadata);
        }
    }

    @Override
    public void generateSQL(Long id) {
        List<ModuleMetadataDTO> moduleMetadataList = metadataClient.getModuleMetadata(id);
        for (ModuleMetadataDTO moduleMetadata : moduleMetadataList) {
            generateSQL(moduleMetadata);
        }
    }

    protected void generateSQL(ModuleMetadataDTO moduleMetadata) {
        List<DomainMetadataDTO> domains = moduleMetadata.getDomains();
        StringBuilder sqlContent = new StringBuilder();
        for (DomainMetadataDTO domain : domains) {
            TableMetadata tableMetadata = new TableMetadata();
            tableMetadata.setName(domain.getName());
            tableMetadata.setNameEnglish(domain.getNameEnglish());
            tableMetadata.setNameCode(domain.getNameCode());
            tableMetadata.setComment(domain.getName());
            Column[] columns = convertProperty2Column(domain);
            tableMetadata.setColumns(columns);
            String creatTableSql = sqlHelper.generateCreateTableSQL(tableMetadata);
            sqlContent.append(creatTableSql);
        }

        Path resourcePath = getResourcePath(moduleMetadata);
        Path sqlPath = resourcePath.resolve(Paths.get("sql", "ddl"));
        notExistThenCreate(sqlPath);
        try (FileWriter fileWriter = new FileWriter(sqlPath.resolve("schema.sql").toFile())) {
            fileWriter.write(sqlContent.toString());
        } catch (IOException e) {
            log.error("创建table.sql配置文件出错！");
            throw new RuntimeException(e);
        }
    }

    private static Column[] convertProperty2Column(DomainMetadataDTO domain) {
        DomainProperty[] properties = domain.getProperties();
        Column[] columns = new Column[properties.length];
        for (int i = 0; i < properties.length; i++) {
            DomainProperty property = properties[i];
            Column column = new Column();
            column.setName(property.getNameEnglish());
            column.setType(TYPE_MAP.get(Strings.toLowerCase(property.getType())));
            column.setNullable(property.getNullable());
            column.setPrimaryKey(property.getPrimaryKey());
            column.setComment(property.getComment());
            columns[i] = column;
        }
        return columns;
    }

    protected void generateConfigfile(ModuleMetadataDTO moduleMetadata) {
        Path resourcePath = getResourcePath(moduleMetadata);
        Path configPath = resourcePath.resolve("application.yml");

        YmlConfig ymlConfig = new YmlConfig();
        BeanUtils.copyProperties(moduleMetadata.getDbConfig(), ymlConfig);
        ymlConfig.setCreateTable(moduleMetadata.getCreateTable());
        ymlConfig.setApplicationName(moduleMetadata.getArtifactId());
        try (FileWriter writer = new FileWriter(configPath.toFile())) {
            configuration.getTemplate("application-yml.ftl").process(ymlConfig, writer);
            log.info("生成文件：{}", configPath);
        } catch (IOException e) {
            log.error("创建application.yml配置文件出错！");
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            log.error("创建application.yml配置文件出错！");
            throw new RuntimeException(e);
        }
    }

    private static Path getResourcePath(ModuleMetadataDTO moduleMetadata) {
        Path parentProjectPath = TEMP_PROJECT_ROOT_PATH.get().resolve(moduleMetadata.getParent().getArtifactId());
        Path projectPath = parentProjectPath.resolve(moduleMetadata.getArtifactId());
        notExistThenCreate(projectPath);

        Path resourcePath = projectPath.resolve(BASE_RESOURCE_PATH);
        notExistThenCreate(resourcePath);
        return resourcePath;
    }

    protected void generateSpringbootStartupClass(ModuleMetadataDTO moduleMetadata) {
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("projectName", moduleMetadata.getArtifactId());
        metaData.put("package", moduleMetadata.getPackageName());

        Path sourceRootPath = getJavaPath(moduleMetadata);
        notExistThenCreate(sourceRootPath);
        String fileName = sourceRootPath
                .resolve(String.format("%sApplication.java", Strings.capFirst(moduleMetadata.getArtifactId()))).toString();
        try (Writer writer = new FileWriter(fileName)) {
            configuration.getTemplate("startup.ftl").process(metaData, writer);
            log.info("生成文件：{}", fileName);
        } catch (IOException e) {
            log.error("获取 应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }

    private void generateSrc(Path projectPath, String packageName) {
        String[] packageNameArr = packageName.split("\\.");
        Path packagePath = Paths.get("", packageNameArr);
        Path resourcePath = projectPath.resolve(BASE_RESOURCE_PATH);
        Path mainSrcPath = projectPath.resolve(BASE_JAVA_PATH).resolve(packagePath);
        Path testSrcPath = projectPath.resolve(BASE_TEST_PATH).resolve(packagePath);
        try {
            Files.createDirectories(mainSrcPath);
            Files.createDirectories(resourcePath);
            Files.createDirectories(testSrcPath);
        } catch (IOException e) {
            log.error("创建项目目录失败！{}", e);
            throw new RuntimeException(e);
        }
    }

    protected static void notExistThenCreate(Path projectPath) {
        if (Files.notExists(projectPath)) {
            try {
                Files.createDirectories(projectPath);
            } catch (IOException e) {
                log.error("创建项目目录失败！{}", e);
                throw new RuntimeException("创建项目目录失败！");
            }
        }
    }

    private void parentProjectAddModule(File parentProjectPom, @NotNull String moduleName) {
        try {
            List<String> lines = Files.readAllLines(parentProjectPom.toPath());
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains("</modules>")) {
                    lines.add(i, String.format("<module>%s</module>", moduleName));
                    FileWriter fileWriter = new FileWriter(parentProjectPom);
                    IOUtils.writeLines(lines, null, fileWriter);
                    fileWriter.close();
                    return;
                }
            }
            throw new RuntimeException("父项目缺少模块标签<modules></modules>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

