package zhangyi.refactoring.mfbook;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;
    private final PriceCode priceCodeStrategy;

    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        priceCodeStrategy = getPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        double thisAmount = 0;
        thisAmount = priceCodeStrategy.amountFor(daysRented);
        return thisAmount;
    }

    private PriceCode getPriceCode(int priceCode) {
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

    public int getFrequentRenterPoints(int daysRented) {
        return priceCodeStrategy.pointsFor(daysRented);
    }

}
