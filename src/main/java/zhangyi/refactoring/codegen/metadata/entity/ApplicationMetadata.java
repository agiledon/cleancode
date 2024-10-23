package zhangyi.refactoring.codegen.metadata.entity;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.IOException;
import java.util.List;

import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

@Data
public class ApplicationMetadata {
    /**
     * 项目名称
     */
    @NotBlank
    private String name;
    /**
     * 应用maven 坐标
     */
    private ProjectMavenCoordinate project;
    /**
     * 应用maven依赖
     */
    private List<ProjectMavenCoordinate> dependencies;
    /**
     * 应用子项目(模块)
     */
    private List<ModuleMetadata> modules;

    private final Configuration configuration;

    public ApplicationMetadata(Configuration configuration) {
        this.configuration = configuration;
    }

    public String generate() throws IOException, TemplateException {
        Template temp = configuration.getTemplate("parent-pom.ftl");
        return processTemplateIntoString(temp, this);
    }
}
