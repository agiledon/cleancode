package zhangyi.refactoring.demeter;

public class Customer {
    private String firstName;
    private String lastName;
    private Wallet myWallet;
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public void pay(float payment) {
        if (myWallet.getTotalMoney() > payment) {
            myWallet.subtractMoney(payment);
        } else {
            //money not enough
        }
    }
}
