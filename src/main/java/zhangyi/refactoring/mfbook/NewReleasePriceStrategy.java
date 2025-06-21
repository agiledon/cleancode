package zhangyi.refactoring.mfbook;

public class NewReleasePriceStrategy implements PriceStrategy {
    @Override
    public double getAmount(int daysRented) {
        if (daysRented <= 0)
            return 0;
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented <= 0)
            return 0;
        int points = 1;
        if (daysRented > 1)
            points++;
        return points;
    }
}