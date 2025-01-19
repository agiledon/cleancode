package zhangyi.refactoring.mfbook;

class NewReleasePrice extends Price {
    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        int points = 1;
        if (daysRented > 1)
            points++;
        return points;
    }
}
