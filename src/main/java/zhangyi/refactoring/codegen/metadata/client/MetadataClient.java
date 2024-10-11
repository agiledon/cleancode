package zhangyi.refactoring.codegen.metadata.client;

import zhangyi.refactoring.codegen.metadata.dto.ApplicationMetadataDTO;
import zhangyi.refactoring.codegen.metadata.dto.ModuleMetadataDTO;

import java.util.List;

public interface MetadataClient {
    ApplicationMetadataDTO getApplicationMetadata(Long id);

    List<ModuleMetadataDTO> getModuleMetadata(Long id);
}
