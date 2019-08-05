package zhangyi.refactoring.mfbook;

public abstract class MoviePrice {
    abstract double amountFor(int daysRented);

    public int pointsFor(int daysRented) {
        return 1;
    }
}
