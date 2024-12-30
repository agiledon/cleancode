package zhangyi.refactoring.mfbook.model;

public class DefaultPriceCode extends PriceCode {
    @Override
    public double amountFor(int daysRented) {
        return 0;
    }
}
