package zhangyi.refactoring.report.engine;

import zhangyi.refactoring.report.servlet.ServletHttpRequest;

public interface Parameter {
    String getName();

    void fill(ServletHttpRequest request);
}
