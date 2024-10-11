package zhangyi.refactoring.codegen.service;

import jakarta.validation.constraints.NotNull;
import zhangyi.refactoring.codegen.metadata.entity.ApplicationMetadata;
import zhangyi.refactoring.codegen.metadata.dto.ApplicationMetadataDTO;
import zhangyi.refactoring.codegen.metadata.entity.GitAddress;
import zhangyi.refactoring.codegen.metadata.entity.ModuleMetadata;

import java.nio.file.Path;

public interface ApplicationService {
    String generateApplication(Long id);

    /**
     * 生成应用
     *
     * @param applicationMetadata 应用元数据
     * @return 应用所在目录
     */
    String generateApplication(ApplicationMetadataDTO applicationMetadata);

    /**
     * 生成 父项目，包含依赖声明及 依赖版本
     *
     * @param applicationMetadata 应用元数据
     * @return
     */
    String generateParentProject(@NotNull ApplicationMetadata applicationMetadata);

    /**
     * 生成 父项目，包含依赖声明及 依赖版本
     *
     * @param applicationMetadata 应用元数据
     * @return
     */
    Path generateParentProject(@NotNull ApplicationMetadataDTO applicationMetadata);

    String generateParentProject(@NotNull Long id);

    /**
     * 生成 子项目，包含依赖声明
     *
     * @param moduleMetadata 组件(子项目)元数据
     * @return
     */
    String generateChildrenProject(@NotNull ModuleMetadata moduleMetadata);

    void generateChildrenProject(@NotNull Long id);

    /**
     * 生成 entity
     *
     * @param applicationId 应用ID
     * @return
     */
    void generateEntity(@NotNull Long applicationId);

    /**
     * 生成 控制层代码
     *
     * @param id 应用ID
     * @return
     */
    void generateController(@NotNull Long id);

    /**
     * 生成 服务层
     *
     * @param id 应用ID
     * @return
     */
    void generateService(@NotNull Long id);

    /**
     * 生成 dao层
     *
     * @param id 应用ID
     * @return
     */
    void generateMapper(@NotNull Long id);

    /**
     * 生成 springboot 启动类
     *
     * @param id 应用ID
     * @return
     */
    void generateSpringBootStartupClass(@NotNull Long id);

    /**
     * 生成springboot 配置文件
     *
     * @param id 应用ID
     */
    void generateConfigFile(@NotNull Long id);

    /**
     * 生成sql脚本
     *
     * @param id 应用ID
     */
    void generateSQL(@NotNull Long id);

    /**
     * 推送本地项目到 git仓库
     *
     * @param gitAddress       git仓库地址
     * @param localProjectPath 项目本地目录
     * @return 项目git地址
     */
    String push2GitRepository(GitAddress gitAddress, Path localProjectPath);
}
