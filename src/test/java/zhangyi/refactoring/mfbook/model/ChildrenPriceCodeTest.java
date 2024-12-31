package zhangyi.refactoring.mfbook.model;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChildrenPriceCodeTest {
    @Test
    public void should_calculate_rental_amount_given_rented_days_is_3() {
        ChildrenPriceCode priceCode = new ChildrenPriceCode();

        double amount = priceCode.amountFor(3);

        assertThat(amount).isEqualTo(1.5d);
    }

    @Test
    public void should_calculate_rental_amount_given_rented_days_is_greater_than_3() {
        ChildrenPriceCode priceCode = new ChildrenPriceCode();

        double amount = priceCode.amountFor(4);

        assertThat(amount).isEqualTo(3.0d);
    }
}