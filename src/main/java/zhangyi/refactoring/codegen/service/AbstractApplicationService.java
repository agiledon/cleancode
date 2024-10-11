package zhangyi.refactoring.codegen.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import zhangyi.refactoring.codegen.metadata.dto.ApplicationMetadataDTO;
import zhangyi.refactoring.codegen.metadata.dto.DomainMetadataDTO;
import zhangyi.refactoring.codegen.metadata.dto.ModuleMetadataDTO;
import zhangyi.refactoring.codegen.metadata.dto.ServiceMetadataDTO;
import zhangyi.refactoring.codegen.metadata.entity.GitAddress;

import java.nio.file.Path;
import java.util.List;

import static zhangyi.refactoring.codegen.service.impl.ApplicationServiceImpl.TEMP_PROJECT_ROOT_PATH;

@Slf4j
public abstract class AbstractApplicationService implements ApplicationService {
    @Override
    public String generateApplication(Long id) {
        String parentProjectFileName = generateParentProject(id);
        generateChildrenProject(id);
        generateEntity(id);
        generateController(id);
        generateService(id);
        generateMapper(id);
        generateConfigFile(id);
        generateSpringBootStartupClass(id);
        generateSQL(id);
        return parentProjectFileName;
    }

    @Override
    public String generateApplication(ApplicationMetadataDTO applicationMetadata) {
        Path parentProjectRootPath = generateParentProject(applicationMetadata);
        applicationMetadata.getModules().forEach(this::generateChildrenProject);
        //推送到git
        String gitRepository = push2GitRepository(applicationMetadata.getGitAddress(), parentProjectRootPath);
        TEMP_PROJECT_ROOT_PATH.remove();
        return gitRepository;
    }

    @Override
    public String push2GitRepository(GitAddress gitAddress, Path localProjectPath) {
        try {
            //init git repoisitory
            Git git = Git.init()
                    .setDirectory(localProjectPath.toFile())
                    .call();
            log.info("Created a new repository at " + git.getRepository().getDirectory());
            //add all file
            git.add().addFilepattern(".").call();
            //commit all file
            git.commit().setMessage("init repository").call();
            //push to remote
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(gitAddress.getAccount(),
                    gitAddress.getPassword());
            String projectGitAddress =
                    String.format("%s/%s/%s.git", gitAddress.getUrl(), gitAddress.getNamespace(),
                            localProjectPath.getFileName());
            git.push().setCredentialsProvider(cp)
                    .setRemote(projectGitAddress)
                    .call();
            log.info("project repository created success! {}", projectGitAddress);
            git.close();
            return projectGitAddress;
        } catch (GitAPIException e) {
            log.error("代码推送Git仓库失败！{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected void generateChildrenProject(ModuleMetadataDTO moduleMetadata) {
        generateEntities(moduleMetadata, moduleMetadata.getDomains());

        List<ServiceMetadataDTO> services = moduleMetadata.getServices();
        for (ServiceMetadataDTO serviceMetadata : services) {
            generateController(moduleMetadata, serviceMetadata);
            generateService(moduleMetadata, serviceMetadata);
        }

        generateModuleMappers(moduleMetadata);
        generateConfigfile(moduleMetadata);
        generateSpringbootStartupClass(moduleMetadata);

        Boolean createTable = moduleMetadata.getCreateTable();
        if (Boolean.TRUE.equals(createTable)) {
            generateSQL(moduleMetadata);
        }
    }

    protected abstract void generateEntities(ModuleMetadataDTO moduleMetadata,
                                             List<DomainMetadataDTO> domainMetadata);

    protected abstract void generateController(ModuleMetadataDTO moduleMetadata,
                                               ServiceMetadataDTO serviceMetadata);

    protected abstract void generateService(ModuleMetadataDTO moduleMetadata,
                                            ServiceMetadataDTO serviceMetadata);

    protected abstract void generateModuleMappers(ModuleMetadataDTO moduleMetadata);

    protected abstract void generateConfigfile(ModuleMetadataDTO moduleMetadata);

    protected abstract void generateSpringbootStartupClass(ModuleMetadataDTO moduleMetadata);

    protected abstract void generateSQL(ModuleMetadataDTO moduleMetadata);
}