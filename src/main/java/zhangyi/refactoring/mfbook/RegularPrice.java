package zhangyi.refactoring.mfbook;

public class RegularPrice extends MoviePrice {
    public RegularPrice() {
    }

    @Override
    double amountFor(int daysRented, double thisAmount) {
        thisAmount += 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}