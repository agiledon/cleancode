package zhangyi.refactoring.report.web;

import zhangyi.refactoring.report.engine.*;

import javax.servlet.http.HttpServletRequest;

public class ParameterController {
    public void fillParameters(HttpServletRequest request, ParameterGraph parameterGraph) {
        parameterGraph.fillValues(request::getParameterValues);
    }
}
