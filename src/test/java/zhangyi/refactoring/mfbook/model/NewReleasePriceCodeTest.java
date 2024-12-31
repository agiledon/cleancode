package zhangyi.refactoring.mfbook.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NewReleasePriceCodeTest {
    @Test
    public void should_calculate_rental_amount() {
        NewReleasePriceCode priceCode = new NewReleasePriceCode();

        double amount = priceCode.amountFor(3);

        assertThat(amount).isEqualTo(9d);
    }

    @Test
    public void should_calculate_points_given_rented_days_is_1() {
        NewReleasePriceCode priceCode = new NewReleasePriceCode();

        int points = priceCode.pointsFor(1);

        assertThat(points).isEqualTo(1);
    }

    @Test
    public void should_calculate_points_given_rented_days_is_greater_than_1() {
        NewReleasePriceCode priceCode = new NewReleasePriceCode();

        int points = priceCode.pointsFor(2);

        assertThat(points).isEqualTo(2);
    }

}