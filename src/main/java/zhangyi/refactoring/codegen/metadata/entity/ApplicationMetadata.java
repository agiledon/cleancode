package zhangyi.refactoring.codegen.metadata.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

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
    private List<String> modules;
}
