package zhangyi.refactoring.report.engine;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ParameterGraph {
    public void fillValues(HttpServletRequest request) {
        for (Parameter para : getParameters()) {
            para.fill(request);
        }
    }

    public List<Parameter> getParameters() {
        return null;
    }
}
