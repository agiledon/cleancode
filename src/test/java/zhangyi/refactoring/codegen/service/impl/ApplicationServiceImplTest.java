package zhangyi.refactoring.codegen.service.impl;

import org.junit.jupiter.api.Test;
import zhangyi.refactoring.codegen.fixtures.ApplicationMetadataFixture;
import zhangyi.refactoring.codegen.metadata.entity.ApplicationMetadata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ApplicationServiceImplTest {
    @Test
    public void should_generate_parent_pom_file_by_ProjectFileWriter() {
        // given
        ApplicationMetadata applicationMetadata = ApplicationMetadataFixture.create(null);

        String fileName = "demo1-parent/pom.xml";
        ProjectFileWriter mockedWriter = mock(ProjectFileWriter.class);
        when(mockedWriter.write(eq(applicationMetadata), anyString())).thenReturn(fileName);

        ApplicationServiceImpl applicationService = new ApplicationServiceImpl(null, null, null, mockedWriter);

        // when
        String generatedFileName = applicationService.generateParentProject(applicationMetadata);

        // then
        verify(mockedWriter).write(eq(applicationMetadata), anyString());
        assertThat(generatedFileName).isEqualTo(fileName);
    }
}