package zhangyi.refactoring.mfbook;

public class Movie {
    private final PriceCode priceCodeStrategy;

    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        priceCodeStrategy = PriceCode.getPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return priceCodeStrategy.amountFor(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return priceCodeStrategy.pointsFor(daysRented);
    }

}
