package zhangyi.refactoring.mfbook;

public class NewReleasePriceCode {
    public double amountFor(int daysRented) {
        double thisAmount = 0;
        thisAmount += daysRented * 3;
        return thisAmount;
    }
}