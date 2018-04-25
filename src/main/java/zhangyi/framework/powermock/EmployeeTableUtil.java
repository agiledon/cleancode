package zhangyi.framework.powermock;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTableUtil {
    public int count() {
        return 0;
    }

    public static final List<Employee> findAll() {
        return new ArrayList<Employee>();
    }

    public void insert(Employee employee) {
        if (existed(employee.getId())) {
            throw new ExistedEmployeeException();
        }
        //insert employee
    }

    public static final void update(Employee employee) {
        if (employee == null) {
            throw new NullEmployeeException();
        }
    }

    public boolean delete(Employee employee) {
        if (existed(employee.getId())) {
            //delete employee
            return true;
        }
        return false;
    }

    private boolean existed(String id) {
        return false;
    }
}
