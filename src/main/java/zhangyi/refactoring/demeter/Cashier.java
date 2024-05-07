package zhangyi.refactoring.demeter;

public class Cashier {
    public void charge(Payable myPayable, float payment) {
        myPayable.pay(payment);
    }
}
