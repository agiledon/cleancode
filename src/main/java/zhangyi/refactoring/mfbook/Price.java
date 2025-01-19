// Price.java
package zhangyi.refactoring.mfbook;

abstract class Price {
    public abstract double getAmount(int daysRented);
    public abstract int getFrequentRenterPoints(int daysRented);

    public static Price createPrice(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new RegularPrice();
            case Movie.NEW_RELEASE:
                return new NewReleasePrice();
            case Movie.CHILDREN:
                return new ChildrenPrice();
            default:
                return new NullPrice(); // 使用NullPrice处理未知的priceCode
        }
    }
}