package zhangyi.refactoring.mfbook;

public abstract class PriceCode {
    public abstract double amountFor(int daysRented);

    public int pointsFor(int daysRented) {
        return 1;
    }
}
