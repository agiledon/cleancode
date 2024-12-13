package zhangyi.refactoring.mfbook;

public class NewReleasePriceCode extends PriceCode {
    @Override
    public double amountFor(int daysRented) {
        double thisAmount = 0;
        thisAmount += daysRented * 3;
        return thisAmount;
    }

    @Override
    public int pointsFor(int daysRented) {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if (daysRented > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }
}