package zhangyi.refactoring.mfbook;

public class NewReleasePrice {
    public NewReleasePrice() {
    }

    public double amountForNewRelease(int daysRented, double thisAmount) {
        thisAmount += daysRented * 3;
        return thisAmount;
    }
}