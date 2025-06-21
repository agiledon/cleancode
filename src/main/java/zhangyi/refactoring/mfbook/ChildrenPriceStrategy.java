package zhangyi.refactoring.mfbook;

public class ChildrenPriceStrategy implements PriceStrategy {
    @Override
    public double getAmount(int daysRented) {
        if (daysRented <= 0)
            return 0;
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 3) * 1.5;
        return amount;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented <= 0)
            return 0;
        return 1;
    }
}