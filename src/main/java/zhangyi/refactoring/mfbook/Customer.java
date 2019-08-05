package zhangyi.refactoring.mfbook;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public double totalAmounts() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.amountFor();
        }
        return totalAmount;
    }

    public int totalPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.pointsFor();
        }
        return frequentRenterPoints;
    }

}
