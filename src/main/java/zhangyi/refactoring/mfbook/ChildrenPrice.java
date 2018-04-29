package zhangyi.refactoring.mfbook;

public class ChildrenPrice {
    public ChildrenPrice() {
    }

    public double amountForChildren(int daysRented, double thisAmount) {
        thisAmount += 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}