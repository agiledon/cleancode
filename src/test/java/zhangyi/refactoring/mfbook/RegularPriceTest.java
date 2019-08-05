package zhangyi.refactoring.mfbook;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegularPriceTest {

    private final RegularPrice regularPrice = new RegularPrice();

    @Test
    public void should_return_2_amounts_if_days_rental_less_than_3() {
        assertThat(regularPrice.amountFor(2)).isEqualTo(2);
    }

    @Test
    public void should_return_2_amounts_add_1point5_times_if_days_rental_greater_than_2() {
        assertThat(regularPrice.amountFor(3)).isEqualTo(3.5);
        assertThat(regularPrice.amountFor(4)).isEqualTo(5);
    }

    @Test
    public void should_return_1_points() {
        assertThat(regularPrice.pointsFor(3)).isEqualTo(1);
    }
}