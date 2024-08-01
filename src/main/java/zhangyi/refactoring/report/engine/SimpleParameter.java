package zhangyi.refactoring.report.engine;

public class SimpleParameter implements Parameter{

    private Object[] value;

    @Override
    public void fill(ParameterRequest request) {
        SimpleParameter simplePara = this;
        String[] values = request.getParameterValues(simplePara.getName());
        simplePara.setValue(values);
    }

    @Override
    public String getName() {
        return null;
    }

    public void setValue(String[] value) {
        this.value = value;
    }
}
