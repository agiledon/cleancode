package zhangyi.refactoring.mfbook;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewReleasePriceTest {

    private final NewReleasePrice newReleasePrice = new NewReleasePrice();

    @Test
    public void should_multiple_3_and_days_rented() {
        assertThat(newReleasePrice.amountFor(2)).isEqualTo(6);
    }

    @Test
    public void should_return_1_point_if_days_rented_less_than_2() {
        assertThat(newReleasePrice.pointsFor(1)).isEqualTo(1);
    }

    @Test
    public void should_return_2_point_if_days_rented_greater_than_1() {
        assertThat(newReleasePrice.pointsFor(2)).isEqualTo(2);
    }
}