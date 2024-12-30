package zhangyi.refactoring.mfbook.model;

public class RegularPriceCode extends PriceCode {
    @Override
    public double amountFor(int daysRented) {
        double thisAmount = 0;
        thisAmount += 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}