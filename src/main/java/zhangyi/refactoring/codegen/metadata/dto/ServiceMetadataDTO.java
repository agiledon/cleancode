package zhangyi.refactoring.codegen.metadata.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServiceMetadataDTO {
    private List<FunctionMetadataDTO> functions;
    private String name;
    private String nameEnglish;
}
