package zhangyi.refactoring.report.engine;

import zhangyi.refactoring.report.servlet.ServletHttpRequest;

public class SimpleParameter implements Parameter{

    private Object[] value;

    @Override
    public String getName() {
        return null;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public void fill(ServletHttpRequest request) {
        SimpleParameter simplePara = this;
        String[] values = request.getParameterValues(simplePara.getName());
        simplePara.setValue(values);
    }
}
