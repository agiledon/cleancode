package zhangyi.framework.powermock;

public class Employee {
    private String id;
    private double salary;

    public Employee(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
