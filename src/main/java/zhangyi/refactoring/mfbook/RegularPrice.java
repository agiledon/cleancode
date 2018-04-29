package zhangyi.refactoring.mfbook;

public class RegularPrice {
    public RegularPrice() {
    }

    double amountFor(int daysRented, double thisAmount) {
        thisAmount += 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;
        return thisAmount;
    }
}