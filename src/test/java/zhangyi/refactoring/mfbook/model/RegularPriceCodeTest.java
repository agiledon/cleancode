package zhangyi.refactoring.mfbook.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegularPriceCodeTest {
    @Test
    public void should_calculate_rental_amount_given_rented_days_is_2() {
        RegularPriceCode priceCode = new RegularPriceCode();

        double amount = priceCode.amountFor(2);

        assertThat(amount).isEqualTo(2d);
    }

    @Test
    public void should_calculate_rental_amount_given_rented_days_is_greater_than_2() {
        RegularPriceCode priceCode = new RegularPriceCode();

        double amount = priceCode.amountFor(3);

        assertThat(amount).isEqualTo(3.5d);
    }
}