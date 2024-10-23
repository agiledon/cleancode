package zhangyi.refactoring.codegen.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMavenCoordinate {
    private String groupId;
    private String artifactId;
    private String version;
}
