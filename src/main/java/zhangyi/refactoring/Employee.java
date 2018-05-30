package zhangyi.refactoring;

public class Employee extends User {
    @Override
    public void login() {
        super.login();
        System.out.println("employee login");
    }
}
