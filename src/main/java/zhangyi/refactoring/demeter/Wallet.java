package zhangyi.refactoring.demeter;

public class Wallet implements Payable {
    private float value;
    public float getTotalMoney() {
        return value;
    }
    public void setTotalMoney(float newValue) {
        value = newValue;
    }
    public void addMoney(float deposit) {
        value += deposit;
    }
    public void subtractMoney(float debit) {
        value -= debit;
    }

    boolean isEnough(float payment) {
        return getTotalMoney() > payment;
    }

    @Override
    public void pay(float payment) {
        if (isEnough(payment)) {
            subtractMoney(payment);
        } else {
            //money not enough
        }
    }
}
