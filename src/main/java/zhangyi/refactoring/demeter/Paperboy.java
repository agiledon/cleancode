package zhangyi.refactoring.demeter;

public class Paperboy {
    public void charge(Customer myCustomer, float payment) {
        myCustomer.pay(payment);
    }

}
