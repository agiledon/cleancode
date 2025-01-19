package zhangyi.refactoring.mfbook;

class NewReleasePrice extends Price {
    @Override
    double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        int points = 1;
        if (daysRented > 1)
            points++;
        return points;
    }
}
