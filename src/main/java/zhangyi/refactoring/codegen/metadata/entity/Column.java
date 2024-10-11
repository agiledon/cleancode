package zhangyi.refactoring.codegen.metadata.entity;

import lombok.Data;

@Data
public class Column {
    private String name;
    private String type;
    private Boolean nullable;
    private String comment;
    private Boolean primaryKey;
}

