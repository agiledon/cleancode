package zhangyi.refactoring.codegen.metadata.entity;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import zhangyi.refactoring.codegen.fixtures.ApplicationMetadataFixture;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationMetadataTest {
    @Test
    public void should_generate_parent_pom_by_template() throws IOException, TemplateException {
        // given
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File(this.getClass().getResource("/templates").getPath()));
        cfg.setDefaultEncoding("UTF-8");

        // when
        ApplicationMetadata applicationMetadata = ApplicationMetadataFixture.create(cfg);
        String generatedPom = applicationMetadata.generate();

        // then
        assertThat(generatedPom).isEqualTo(provideExpectedPom());
    }

    private static String provideExpectedPom() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>com.zhangyi</groupId>\n" +
                "    <artifactId>demo-parent</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "    <packaging>pom</packaging>\n" +
                "\n" +
                "    <modules>\n" +
                "        <module>module01</module>\n" +
                "        <module>module02</module>\n" +
                "        <module>module03</module>\n" +
                "        <module>module04</module>\n" +
                "    </modules>\n" +
                "\n" +
                "    <properties>\n" +
                "        <java.version>1.8</java.version>\n" +
                "        <maven.compiler.source>8</maven.compiler.source>\n" +
                "        <maven.compiler.target>8</maven.compiler.target>\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "    </properties>\n" +
                "</project>";
    }

}