package zhangyi.refactoring.mfbook;

public class TextStatementFormatter implements StatementFormatter {
    @Override
    public String format(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (Rental each : customer.getRentals()) {
            double thisAmount = each.getCharge();

            frequentRenterPoints += each.getFrequentRenterPoints();

            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }
}
