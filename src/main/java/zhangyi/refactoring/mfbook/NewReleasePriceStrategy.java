package zhangyi.refactoring.mfbook;

public class NewReleasePriceStrategy implements PriceStrategy {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        int points = 1;
        if (daysRented > 1)
            points++;
        return points;
    }
}