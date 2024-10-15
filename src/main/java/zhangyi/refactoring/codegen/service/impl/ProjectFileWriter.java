package zhangyi.refactoring.codegen.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zhangyi.refactoring.codegen.metadata.entity.ApplicationMetadata;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@AllArgsConstructor
@Slf4j
public class ProjectFileWriter {
    private final Configuration configuration;

    public String write(ApplicationMetadata applicationMetadata, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Template temp = configuration.getTemplate("parent-pom.ftl");
            temp.process(applicationMetadata, writer);
            return fileName;
        } catch (IOException e) {
            log.error("获取应用父工程pom文件出错！{}", e);
            throw new RuntimeException("获取 应用父工程pom文件出错！");
        } catch (TemplateException e) {
            log.error("生成应用父工程pom文件出错！{}", e);
            throw new RuntimeException("生成应用父工程pom文件出错！");
        }
    }
}