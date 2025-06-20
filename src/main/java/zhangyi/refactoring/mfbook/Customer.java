package zhangyi.refactoring.mfbook;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            result.append("\t").append(each.getMovie().getTitle())
                    .append("\t").append(each.getAmount()).append("\n");
        }

        result.append("Amount owed is ").append(getTotalAmount()).append("\n");
        result.append("You earned ").append(getFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }

    private double getTotalAmount() {
        return rentals.stream()
                .mapToDouble(Rental::getAmount)
                .sum();
    }

    private int getFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
