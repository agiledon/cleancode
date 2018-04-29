package zhangyi.refactoring.report.web;

import zhangyi.refactoring.report.engine.*;
import zhangyi.refactoring.report.servlet.ServletHttpRequest;

public class ParameterCollector {
    public void fillParameters(ServletHttpRequest request, ParameterGraph parameterGraph) {
        for (Parameter para : parameterGraph.getParmaeters()) {
            if (para instanceof SimpleParameter) {
                ((SimpleParameter) para).fill(request);
            } else {
                if (para instanceof ItemParameter) {
                    ((ItemParameter) para).fill(request);
                } else {
                    ((TableParameter) para).fill(request);
                }
            }
        }
    }

}
