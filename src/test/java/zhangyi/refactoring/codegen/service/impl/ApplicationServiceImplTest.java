package zhangyi.refactoring.codegen.service.impl;

import org.junit.jupiter.api.Test;
import zhangyi.refactoring.codegen.metadata.entity.ApplicationMetadata;
import zhangyi.refactoring.codegen.metadata.entity.ProjectMavenCoordinate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ApplicationServiceImplTest {
    @Test
    public void should_generate_parent_pom_file_by_ProjectFileWriter() {
        // given
        ApplicationMetadata applicationMetadata = new ApplicationMetadata();

        String projectName = "demo3-parent";
        applicationMetadata.setName(projectName);

        String projectGroup = "com.xm";
        String projectArtifact = "demo1-parent";
        String projectVersion = "1.0-SNAPSHOT";
        ProjectMavenCoordinate appMavenCoordinate = new ProjectMavenCoordinate(projectGroup, projectArtifact, projectVersion);

        applicationMetadata.setProject(appMavenCoordinate);
        applicationMetadata.setModules(Arrays.asList("module01", "module02", "module03", "module04"));

        String templateName = "parent-pom.ftl";
        String fileName = "demo1-parent/pom.xml";
        ProjectFileWriter mockedWriter = mock(ProjectFileWriter.class);
        when(mockedWriter.write(eq(applicationMetadata), eq(fileName))).thenReturn(fileName);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl(null, null, null, mockedWriter);

        // when
        String generatedFileName = applicationService.generateParentProject(applicationMetadata);

        // then
        verify(mockedWriter).write(eq(applicationMetadata), eq(fileName));
        assertThat(generatedFileName).isEqualTo(fileName);
    }
}