package zhangyi.refactoring.mfbook;

public class NewReleasePrice extends MoviePrice {
    public NewReleasePrice() {
    }

    @Override
    public double amountFor(int daysRented) {
        double thisAmount = 0.0;
        thisAmount += daysRented * 3;
        return thisAmount;
    }

    @Override
    public int pointsFor(int daysRented) {
        if (daysRented > 1) {
            return 2;
        }
        return 1;
    }
}