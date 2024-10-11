package zhangyi.refactoring.codegen.metadata.entity;

import lombok.Data;

@Data
public class TableMetadata {
    private String name;
    private String nameCode;
    private String nameEnglish;
    private String comment;
    private Column[] columns;
}
