package zhangyi.refactoring.codegen.metadata.entity;

import lombok.Data;

@Data
public class YmlConfig {
    private String host;
    private String port;
    private String schema;
    private String username;
    private String password;

    private Boolean createTable;
    private String applicationName;
}
