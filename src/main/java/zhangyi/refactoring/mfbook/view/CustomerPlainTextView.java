package zhangyi.refactoring.mfbook.view;

import zhangyi.refactoring.mfbook.model.Customer;
import zhangyi.refactoring.mfbook.model.Rental;

public class CustomerPlainTextView implements CustomerView {
    private final Customer customer;

    public CustomerPlainTextView(Customer customer) {
        this.customer = customer;
    }

    @Override
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