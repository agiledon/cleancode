package zhangyi.refactoring.mfbook;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;
    private final ChildrenPrice childrenPrice = new ChildrenPrice();
    private final NewReleasePrice newReleasePrice = new NewReleasePrice();
    private final RegularPrice regularPrice = new RegularPrice();

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
                thisAmount = regularPrice.amountFor(daysRented, thisAmount);
                break;
            case NEW_RELEASE:
                thisAmount = newReleasePrice.amountFor(daysRented, thisAmount);
                break;
            case CHILDREN:
                thisAmount = childrenPrice.amountFor(daysRented, thisAmount);
                break;
        }
        return thisAmount;
    }
}
