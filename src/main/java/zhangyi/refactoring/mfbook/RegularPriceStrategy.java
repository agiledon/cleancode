package zhangyi.refactoring.mfbook;

public class RegularPriceStrategy implements PriceStrategy {
    @Override
    public double getAmount(int daysRented) {
        if (daysRented <= 0)
            return 0;
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented <= 0)
            return 0;
        return 1;
    }
}