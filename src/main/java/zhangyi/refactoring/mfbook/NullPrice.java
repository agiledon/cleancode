package zhangyi.refactoring.mfbook;

class NullPrice extends Price {
    @Override
    public double getAmount(int daysRented) {
        return 0;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 0;
    }
}
