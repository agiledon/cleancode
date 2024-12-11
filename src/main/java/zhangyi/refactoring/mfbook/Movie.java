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
        //determine amounts for rental line
        switch (priceCode) {
            case REGULAR:
                thisAmount = amountForRegular(daysRented);
                break;
            case NEW_RELEASE:
                thisAmount = amountForNewRelease(daysRented);
                break;
            case CHILDREN:
                thisAmount = amountForChildren(daysRented);
                break;
        }
        return thisAmount;
    }

    private static double amountForChildren(int daysRented) {
        double thisAmount = 0;
        thisAmount += 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }

    private static double amountForNewRelease(int daysRented) {
        double thisAmount = 0;
        thisAmount += daysRented * 3;
        return thisAmount;
    }

    private static double amountForRegular(int daysRented) {
        double thisAmount = 0;
        thisAmount += 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
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
