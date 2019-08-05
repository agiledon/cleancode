package zhangyi.refactoring.mfbook;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChildrenPriceTest {
    private final MoviePrice childrenPrice = new ChildrenPrice();

    @Test
    public void should_return_1point5_amounts_if_days_rental_less_than_4() {
        assertThat(childrenPrice.amountFor(3)).isEqualTo(1.5);
    }

    @Test
    public void should_return_1point5_amounts_add_1point5_times_if_days_rental_greater_than_3() {
        assertThat(childrenPrice.amountFor(4)).isEqualTo(3);
        assertThat(childrenPrice.amountFor(5)).isEqualTo(4.5);
    }

    @Test
    public void should_return_1_points() {
        assertThat(childrenPrice.pointsFor(3)).isEqualTo(1);
    }
}