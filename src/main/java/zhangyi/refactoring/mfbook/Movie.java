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

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }

    public double amountFor(int daysRented) {
        double thisAmount = 0;
        switch (this.getPriceCode()) {
            case REGULAR:
                thisAmount = amountForRegular(daysRented, thisAmount);
                break;
            case NEW_RELEASE:
                thisAmount = amountForNewRelease(daysRented, thisAmount);
                break;
            case CHILDREN:
                thisAmount = amountForChildren(daysRented, thisAmount);
                break;
        }
        return thisAmount;
    }

    private double amountForChildren(int daysRented, double thisAmount) {
        thisAmount += 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }

    private double amountForNewRelease(int daysRented, double thisAmount) {
        thisAmount += daysRented * 3;
        return thisAmount;
    }

    private double amountForRegular(int daysRented, double thisAmount) {
        thisAmount += 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}
