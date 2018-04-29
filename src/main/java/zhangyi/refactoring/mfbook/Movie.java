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
        MoviePrice price = createPrice();
        return price.amountFor(daysRented, thisAmount);
    }

    private MoviePrice createPrice() {
        switch (this.getPriceCode()) {
            case REGULAR:
                return new RegularPrice();
            case NEW_RELEASE:
                return new NewReleasePrice();
            default:
                return new ChildrenPrice();
        }
    }
}
