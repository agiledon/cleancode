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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            double thisAmount = amountFor(each);

            // add frequent renter points
            frequentRenterPoints += frequentRenterPointsFor(each);

            // show figures for this rental
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        // add footer lines
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private double amountFor(Rental aRental) {
        double result = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (aRental.getDaysRented() > 2)
                    result += (aRental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDREN:
                result += 1.5;
                if (aRental.getDaysRented() > 3)
                    result += (aRental.getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }

    private int frequentRenterPointsFor(Rental aRental) {
        int points = 1;
        // add bonus for a two day new release rental
        if (aRental.getMovie().getPriceCode() == Movie.NEW_RELEASE && aRental.getDaysRented() > 1)
            points++;
        return points;
    }
}
