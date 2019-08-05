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

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";

        int frequentRenterPoints = totalPoints();

        for (Rental rental : rentals) {
            //show figures
            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.amountFor()) + "\n";
        }

        double totalAmount = totalAmounts();

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        return result;
    }

    private double totalAmounts() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.amountFor();
        }
        return totalAmount;
    }

    private int totalPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.pointsFor();
        }
        return frequentRenterPoints;
    }

}
