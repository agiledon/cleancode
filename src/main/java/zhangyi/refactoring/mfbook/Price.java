package zhangyi.refactoring.mfbook;

abstract class Price {
    abstract double getAmount(int daysRented);
    abstract int getFrequentRenterPoints(int daysRented);
}
