package zhangyi.refactoring.report.engine;

import javax.servlet.http.HttpServletRequest;

public interface Parameter {
    void fill(HttpServletRequest request);

    String getName();
}
