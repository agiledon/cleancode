package zhangyi.refactoring.codegen.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zhangyi.refactoring.codegen.metadata.entity.TableMetadata;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;

@Component
@AllArgsConstructor
@Slf4j
public class SqlHelper {
    private final Configuration configuration;

    /**
     * 根据表元数据 生成 创建表的SQL语句
     *
     * @param tableMetadata 表 元数据
     * @return
     */
    public String generateCreateTableSQL(TableMetadata tableMetadata) {
        try (Writer writer = new CharArrayWriter()) {
            configuration.getTemplate("sql-createTable.ftl").process(Collections.singletonMap("table", tableMetadata),
                    writer);
            return writer.toString();
        } catch (IOException e) {
            log.error("获取 创建表 SQL模板出错！{}", e);
            throw new RuntimeException("获取 创建表 SQL模板出错！");
        } catch (TemplateException e) {
            log.error("生成创建表 SQL出错！{}", e);
            throw new RuntimeException("生成创建表 SQL出错！");
        }
    }
}

