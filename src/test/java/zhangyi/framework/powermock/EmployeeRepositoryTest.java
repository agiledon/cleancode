package zhangyi.framework.powermock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeTableUtil.class)
public class EmployeeRepositoryTest {
    private EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new EmployeeRepository();
    }

    @Test
    public void should_mock_static_method() {
        List<Employee> employee = new ArrayList<Employee>();
        employee.add(new Employee("1"));
        employee.add(new Employee("2"));

        PowerMockito.mockStatic(EmployeeTableUtil.class);
        when(EmployeeTableUtil.findAll()).thenReturn(employee);

        List<Employee> employees = repository.findAll();
        assertThat(employees.size(), is(2));
        assertThat(employees.get(0).getId(), is("1"));
        assertThat(employees.get(1).getId(), is("2"));

        PowerMockito.verifyStatic(EmployeeTableUtil.class);
        EmployeeTableUtil.findAll();
    }

    @Test
    public void should_mock_exception_for_command_method_in_mock_object() {
        Employee employee = new Employee("1");

        PowerMockito.mockStatic(EmployeeTableUtil.class);
        PowerMockito.doThrow(new NullEmployeeException()).when(EmployeeTableUtil.class);
        EmployeeTableUtil.update(employee);

        assertThat(repository.update(employee), is(false));
    }

    @Test
    public void should_mock_private_method() throws Exception {
        Employee employee = new Employee("1");

        EmployeeTableUtil util = PowerMockito.spy(new EmployeeTableUtil());
        PowerMockito.when(util, "existed", anyString()).thenReturn(true);

        repository.setTableUtil(util);

        assertThat(repository.insert(employee), is(false));
        assertThat(repository.delete(employee), is(true));
    }

    @Test
    public void should_test_private_method() throws Exception {
        Employee employee = new Employee("1");
        employee.setSalary(8000d);

        double result = Whitebox.<Double>invokeMethod(repository, "bonus", employee);
        assertThat(result, is(800d));
    }
}