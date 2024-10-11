package zhangyi.refactoring.codegen.metadata.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PropertyMetadata {
    private final boolean primaryKey;
    private final String nameEnglish;
    private final String type;
    private String comment;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public String getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }
}
