package zhangyi.refactoring.mfbook;

public class HtmlStatementFormatter implements StatementFormatter {
    @Override
    public String format(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "<h1>Rental Record for <em>" + customer.getName() + "</em></h1><p>\n";

        for (Rental each : customer.getRentals()) {
            double thisAmount = each.getCharge();

            frequentRenterPoints += each.getFrequentRenterPoints();

            result += each.getMovie().getTitle() + ": " + thisAmount + "<br>\n";
            totalAmount += thisAmount;
        }

        result += "<p>You owe <em>" + totalAmount + "</em><p>\n";
        result += "On this rental you earned <em>" + frequentRenterPoints + "</em> frequent renter points<p>";
        return result;
    }
}
