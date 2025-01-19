package zhangyi.refactoring.mfbook;

class RegularPrice extends Price {
    @Override
    public double getAmount(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
