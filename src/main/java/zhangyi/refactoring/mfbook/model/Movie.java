package zhangyi.refactoring.mfbook.model;

public class Movie {
    private final PriceCode priceCode;

    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = PriceCode.getPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return priceCode.amountFor(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return priceCode.pointsFor(daysRented);
    }

}
