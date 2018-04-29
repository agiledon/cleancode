package zhangyi.refactoring.mfbook;

public class NewReleasePrice extends MoviePrice {
    public NewReleasePrice() {
    }

    @Override
    public double amountFor(int daysRented, double thisAmount) {
        thisAmount += daysRented * 3;
        return thisAmount;
    }
}