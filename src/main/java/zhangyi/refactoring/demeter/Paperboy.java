package zhangyi.refactoring.demeter;

public class Paperboy {
    public void charge(Customer myCustomer, float payment) {
        pay(myCustomer, payment);
    }

    private void pay(Customer myCustomer, float payment) {
        Wallet theWallet = myCustomer.getWallet();
        if (theWallet.getTotalMoney() > payment) {
            theWallet.subtractMoney(payment);
        } else {
            //money not enough
        }
    }
}
