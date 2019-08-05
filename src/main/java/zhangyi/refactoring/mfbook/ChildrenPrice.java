package zhangyi.refactoring.mfbook;

public class ChildrenPrice extends MoviePrice {
    public ChildrenPrice() {
    }

    @Override
    public double amountFor(int daysRented) {
        double thisAmount = 0.0;
        thisAmount += 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}