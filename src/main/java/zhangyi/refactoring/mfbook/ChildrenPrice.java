package zhangyi.refactoring.mfbook;

class ChildrenPrice extends Price {
    @Override
    double getAmount(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
