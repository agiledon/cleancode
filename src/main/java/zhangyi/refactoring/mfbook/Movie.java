// Movie.java
package zhangyi.refactoring.mfbook;

class Movie {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.price = Price.createPrice(priceCode); // 调用Price的工厂方法
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        return price.getAmount(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}