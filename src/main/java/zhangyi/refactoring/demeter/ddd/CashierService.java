package zhangyi.refactoring.demeter.ddd;

import zhangyi.refactoring.demeter.Cashier;
import zhangyi.refactoring.demeter.Customer;

public class CashierService {
    private CashierRepository cashierRepo;
    private CustomerRepository customerRepo;

    public void charge(String cashierId, String customerId, float payment) {
        Cashier cashier = cashierRepo.cashierOf(cashierId);
        Customer customer = customerRepo.customerOf(customerId);
        cashier.charge(customer, payment);

        customerRepo.save(customer);
    }
}
