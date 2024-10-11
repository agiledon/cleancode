package zhangyi.refactoring.codegen.metadata.dto;

import lombok.Data;

@Data
public class DomainMetadataDTO {
    private String name;
    private String nameEnglish;
    private String nameCode;
    private DomainProperty[] properties;
}
