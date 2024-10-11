package zhangyi.refactoring.codegen.metadata.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ModuleMetadata {
    /**
     * 项目名称
     */
    @NotBlank
    private String name;
    /**
     * 项目 group, 默认为 父项目的group
     */
    private String group;
    /**
     * 项目 artifact,默认为 项目name
     */
    private String artifact;
    /**
     * 项目 包名,默认为 项目group.artifact
     */
    private String packageName;

    /**
     * 父项目 maven 坐标
     */
    private ProjectMavenCoordinate parent;
    /**
     * 应用maven依赖
     */
    private List<ProjectMavenCoordinate> dependencies;
}
