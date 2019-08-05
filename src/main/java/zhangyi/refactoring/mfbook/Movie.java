package zhangyi.refactoring.mfbook;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private String title;
    private MoviePrice price;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.price = createPrice(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double amountFor(int daysRented) {
        return price.amountFor(daysRented);
    }

    public int pointsFor(int daysRented) {
        return price.pointsFor(daysRented);
    }

    private MoviePrice createPrice(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                return new RegularPrice();
            case NEW_RELEASE:
                return new NewReleasePrice();
            default:
                return new ChildrenPrice();
        }
    }
}
