package zhangyi.refactoring.mfbook;

public interface PriceStrategy {
    double getAmount(int daysRented);

    int getFrequentRenterPoints(int daysRented);
}