package zhangyi.refactoring.mfbook;

public class RegularPriceStrategy implements PriceStrategy {
    @Override
    public double getAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}