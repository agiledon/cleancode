package zhangyi.refactoring.demeter;

public class Customer {
    private String customerId;

    private String firstName;
    private String lastName;
    private Wallet myWallet;

    public void pay(float payment) {
        if (myWallet.getTotalMoney() > payment) {
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
