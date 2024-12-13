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
        PriceCode priceCodeStrategy = null;
        //determine amounts for rental line
        switch (priceCode) {
            case REGULAR:
                priceCodeStrategy = new RegularPriceCode();
                break;
            case NEW_RELEASE:
                priceCodeStrategy = new NewReleasePriceCode();
                break;
            case CHILDREN:
                priceCodeStrategy = new ChildrenPriceCode();
                break;
        }
        thisAmount = priceCodeStrategy.amountFor(daysRented);
        return thisAmount;
    }

    public int getFrequentRenterPoints(int daysRented) {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if ((priceCode == NEW_RELEASE) && daysRented > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
