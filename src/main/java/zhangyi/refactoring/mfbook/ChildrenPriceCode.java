package zhangyi.refactoring.mfbook;

public class ChildrenPriceCode {
    public double amountFor(int daysRented) {
        double thisAmount = 0;
        thisAmount += 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}