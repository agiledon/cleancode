package zhangyi.refactoring.demeter.ddd;

import zhangyi.refactoring.demeter.Customer;

public interface CustomerRepository {
    Customer customerOf(String customerId); // load

    void save(Customer customer);
}
