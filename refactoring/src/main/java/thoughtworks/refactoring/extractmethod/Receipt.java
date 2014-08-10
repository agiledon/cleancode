package thoughtworks.refactoring.extractmethod;

import java.util.List;

public class Receipt {
    private List<Double> discounts;
    private List<Double> itemTotals;

    public double calculateGrandTotal() {
        double subTotal = 0;
        for (double itemTotal : itemTotals) {
            subTotal += itemTotal;
        }

        if (discounts.size() > 0) {
            for (double discount : discounts)
                subTotal -= discount;
        }

        double tax = subTotal * 0.065;
        subTotal += tax;
        return subTotal;
    }
}
