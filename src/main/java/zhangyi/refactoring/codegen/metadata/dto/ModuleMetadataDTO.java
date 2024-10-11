package zhangyi.refactoring.codegen.metadata.dto;

import lombok.Data;
import zhangyi.refactoring.codegen.metadata.entity.YmlConfig;

import java.util.List;

@Data
public class ModuleMetadataDTO {
    private String packageName;
    private ProjectMetadataDTO parent;
    private String artifactId;
    private List<DomainMetadataDTO> domains;
    private List<ServiceMetadataDTO> services;
    private Boolean createTable;
    private YmlConfig dbConfig;
}
