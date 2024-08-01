package zhangyi.refactoring.report.engine;

import java.util.List;

public class ParameterGraph {
    public void fillValues(ParameterRequest request) {
        for (Parameter para : getParameters()) {
            para.fill(request);
        }
    }

    public List<Parameter> getParameters() {
        return null;
    }
}
