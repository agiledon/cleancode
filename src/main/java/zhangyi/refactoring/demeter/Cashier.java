package zhangyi.refactoring.demeter;

public class Cashier {
    public void charge(Customer myCustomer, float payment) {
        Wallet theWallet = myCustomer.getWallet();
        if (theWallet.getTotalMoney() > payment) {
            theWallet.subtractMoney(payment);
        } else {
            //money not enough
        }
    }
}
