package zhangyi.refactoring.mfbook;

public class Statement {
    public static String statement(Customer customer) {
        String result = "Rental Record for " + customer.getName() + "\n";

        for (Rental rental : customer.getRentals()) {
            //show figures
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amountFor()) + "\n";
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(customer.totalAmounts()) + "\n";
        result += "You earned " + String.valueOf(customer.totalPoints()) +
                " frequent renter points";
        return result;
    }
}
