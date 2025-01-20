package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewReleasePriceTest {
    private NewReleasePrice newReleasePrice;

    @Before
    public void setUp() {
        newReleasePrice = new NewReleasePrice();
    }

    @Test
    public void test_get_amount_days_rented_less_than_or_equal_to_1() {
        assertEquals(3.0, newReleasePrice.getAmount(1), 0.01);
    }

    @Test
    public void test_get_amount_days_rented_greater_than_1() {
        assertEquals(6.0, newReleasePrice.getAmount(2), 0.01);
        assertEquals(9.0, newReleasePrice.getAmount(3), 0.01);
    }

    @Test
    public void test_get_frequent_renter_points_days_rented_less_than_or_equal_to_1() {
        assertEquals(1, newReleasePrice.getFrequentRenterPoints(1));
    }

    @Test
    public void test_get_frequent_renter_points_days_rented_greater_than_1() {
        assertEquals(2, newReleasePrice.getFrequentRenterPoints(2));
        assertEquals(2, newReleasePrice.getFrequentRenterPoints(3));
    }
}
