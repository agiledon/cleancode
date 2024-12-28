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

    public int getTotalPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            double thisAmount = rental.getCharge();
            totalAmount += thisAmount;
        }
        return totalAmount;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
