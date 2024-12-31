package zhangyi.refactoring.mfbook.model;

public class DefaultPriceCode extends PriceCode {
    @Override
    public double amountFor(int daysRented) {
        return 0;
    }

    @Override
    public int pointsFor(int daysRented) {
        return 0;
    }
}
