package zhangyi.refactoring.demeter;

public class Customer {
    private String customerId;

    private String firstName;
    private String lastName;
    private Wallet myWallet;
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public Wallet getWallet(){
        return myWallet;
    }
}
