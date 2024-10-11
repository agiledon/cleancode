package zhangyi.refactoring.codegen.metadata.dto;

import lombok.Data;
import org.springframework.context.aot.AbstractAotProcessor;
import zhangyi.refactoring.codegen.metadata.entity.GitAddress;

import java.util.List;

@Data
public class ApplicationMetadataDTO {
    private ProjectMetadataDTO project;
    private List<ModuleMetadataDTO> modules;
    private GitAddress gitAddress;
}
