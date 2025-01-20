package zhangyi.refactoring.mfbook;

public class CustomerStatementView implements StatementGenerator {
    private Customer customer;

    public CustomerStatementView(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String generateStatement() {
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(customer.getName()).append("\n");

        for (Rental each : customer.getRentals()) {
            result.append("\t").append(each.getMovie().getTitle()).append("\t")
                    .append(each.getAmount()).append("\n");
        }

        result.append("Amount owed is ").append(customer.getTotalCharge()).append("\n");
        result.append("You earned ").append(customer.getTotalFrequentRenterPoints()).append(" frequent renter points");

        return result.toString();
    }
}