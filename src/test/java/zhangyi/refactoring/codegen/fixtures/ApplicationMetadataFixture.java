package zhangyi.refactoring.codegen.fixtures;

import freemarker.template.Configuration;
import zhangyi.refactoring.codegen.metadata.entity.ApplicationMetadata;
import zhangyi.refactoring.codegen.metadata.entity.ModuleMetadata;
import zhangyi.refactoring.codegen.metadata.entity.ProjectMavenCoordinate;

import java.util.Arrays;

public class ApplicationMetadataFixture {
    public static ApplicationMetadata create(Configuration configuration) {
        ApplicationMetadata applicationMetadata = new ApplicationMetadata(configuration);

        String projectName = "demo-parent";
        applicationMetadata.setName(projectName);

        String projectGroup = "com.zhangyi";
        String projectArtifact = "demo-parent";
        String projectVersion = "1.0-SNAPSHOT";
        ProjectMavenCoordinate appMavenCoordinate = new ProjectMavenCoordinate(projectGroup, projectArtifact, projectVersion);

        applicationMetadata.setProject(appMavenCoordinate);

        ModuleMetadata m01 = new ModuleMetadata("module01");
        ModuleMetadata m02 = new ModuleMetadata("module02");
        ModuleMetadata m03 = new ModuleMetadata("module03");
        ModuleMetadata m04 = new ModuleMetadata("module04");
        applicationMetadata.setModules(Arrays.asList(m01, m02, m03, m04));
        return applicationMetadata;
    }
}
