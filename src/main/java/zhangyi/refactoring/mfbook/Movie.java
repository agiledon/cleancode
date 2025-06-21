package zhangyi.refactoring.mfbook;

public class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private String title;
    private MovieType movieType;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.movieType = createMovieType(priceCode);
    }

    private MovieType createMovieType(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                return MovieType.REGULAR;
            case NEW_RELEASE:
                return MovieType.NEW_RELEASE;
            case CHILDREN:
                return MovieType.CHILDREN;
            default:
                throw new IllegalArgumentException("Unknown price code: " + priceCode);
        }
    }

    public int getPriceCode() {
        switch (movieType) {
            case REGULAR:
                return REGULAR;
            case NEW_RELEASE:
                return NEW_RELEASE;
            case CHILDREN:
                return CHILDREN;
            default:
                throw new IllegalStateException("Unknown movie type");
        }
    }

    public void setPriceCode(int priceCode) {
        this.movieType = createMovieType(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        return movieType.getPriceStrategy().getAmount(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return movieType.getPriceStrategy().getFrequentRenterPoints(daysRented);
    }
}
