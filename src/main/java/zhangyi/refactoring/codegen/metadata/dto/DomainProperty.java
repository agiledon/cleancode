package zhangyi.refactoring.codegen.metadata.dto;

import lombok.Data;

@Data
public class DomainProperty {
    private String nameEnglish;
    private String type;
    private Boolean nullable;
    private String comment;
    private Boolean primaryKey;
}
