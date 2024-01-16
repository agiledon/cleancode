package zhangyi.refactoring.convertanonymoustoinner;

public class Class {
    public Interface method() {
        final int i = 0;
        return new Interface() {
            public int publicMethod() {
                return i;
            }
        };
    }
}
