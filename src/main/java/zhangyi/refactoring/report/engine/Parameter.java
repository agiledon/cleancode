package zhangyi.refactoring.report.engine;

public interface Parameter {
    void fill(ParameterRequest request);

    String getName();
}
