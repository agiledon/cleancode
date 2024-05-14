package zhangyi.refactoring.demeter;

public class Customer implements Payable {
    private String customerId;

    private String firstName;
    private String lastName;
    private Wallet myWallet;

    @Override
    public void pay(float payment) {
        if (myWallet.isEnough(payment)) {
            myWallet.subtractMoney(payment);
        } else {
            //money not enough
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
