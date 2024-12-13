package zhangyi.refactoring.mfbook;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        double thisAmount = 0;
        PriceCode priceCodeStrategy = getPriceCode();
        thisAmount = priceCodeStrategy.amountFor(daysRented);
        return thisAmount;
    }

    private PriceCode getPriceCode() {
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
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if ((priceCode == NEW_RELEASE) && daysRented > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
