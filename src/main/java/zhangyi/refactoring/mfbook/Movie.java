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

    public double getAmount(int daysRented) {
        double amount = 0;
        switch (priceCode) {
            case REGULAR:
                amount += 2;
                if (daysRented > 2)
                    amount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                amount += daysRented * 3;
                break;
            case CHILDREN:
                amount += 1.5;
                if (daysRented > 3)
                    amount += (daysRented - 3) * 1.5;
                break;
        }
        return amount;
    }

    public int getFrequentRenterPoints(int daysRented) {
        int points = 1;
        // add bonus for a two days new release rental
        if ((priceCode == NEW_RELEASE) && daysRented > 1)
            points++;
        return points;
    }
}
