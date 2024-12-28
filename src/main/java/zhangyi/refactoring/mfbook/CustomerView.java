package zhangyi.refactoring.mfbook;

public class CustomerView {
    private final Customer customer;

    public CustomerView(Customer customer) {
        this.customer = customer;
    }

    public String statement() {
        String result = "Rental Record for " + customer.getName() + "\n";
        for (Rental rental : customer.getRentals()) {
            //show figures
            result += "\t" + rental.getMovieTitle() + "\t" + rental.getCharge() + "\n";
        }

        //add footer lines
        result += "Amount owed is " + customer.getTotalAmount() + "\n";
        result += "You earned " + customer.getTotalPoints() +
                " frequent renter points";
        return result;
    }
}