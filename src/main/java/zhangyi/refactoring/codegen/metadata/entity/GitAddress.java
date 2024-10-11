package zhangyi.refactoring.codegen.metadata.entity;

import lombok.Data;

@Data
public class GitAddress {
    private String account;
    private String password;
    private String url;
    private String namespace;
}
