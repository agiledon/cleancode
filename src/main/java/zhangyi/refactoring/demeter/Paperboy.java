package zhangyi.refactoring.demeter;

public class Paperboy {
    private Customer myCustomer;
    public void pay(float payment) {
        Wallet theWallet = myCustomer.getWallet();
        if (theWallet.getTotalMoney() > payment) {
            theWallet.subtractMoney(payment);
        } else {
            //money not enough
        }
    }
}
