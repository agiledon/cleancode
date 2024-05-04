package zhangyi.refactoring.demeter;

public class Cashier {
    public void charge(Customer myCustomer, float payment) {
        myCustomer.pay(payment);
    }
}
