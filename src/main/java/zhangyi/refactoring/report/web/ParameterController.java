package zhangyi.refactoring.report.web;

import zhangyi.refactoring.report.engine.*;

import javax.servlet.http.HttpServletRequest;

public class ParameterController {
    public void fillParameters(HttpServletRequest request, ParameterGraph parameterGraph) {
        for (Parameter para : parameterGraph.getParameters()) {
            if (para instanceof SimpleParameter) {
                ((SimpleParameter) para).fillSimpleParameter(request);
            } else {
                if (para instanceof ItemParameter) {
                    ((ItemParameter) para).fillItemParameter(request);
                } else {
                    ((TableParameter) para).fillTableParameter(request);
                }
            }
        }
    }

}
