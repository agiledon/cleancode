package zhangyi.refactoring.mfbook.model;

public abstract class PriceCode {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    public static PriceCode getPriceCode(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                return new RegularPriceCode();
            case NEW_RELEASE:
                return new NewReleasePriceCode();
            case CHILDREN:
                return new ChildrenPriceCode();
            default:
                return new DefaultPriceCode();
        }
    }

    public abstract double amountFor(int daysRented);

    public int pointsFor(int daysRented) {
        return 1;
    }
}
