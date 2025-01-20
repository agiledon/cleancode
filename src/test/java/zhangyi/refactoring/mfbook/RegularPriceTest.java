package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegularPriceTest {
    private RegularPrice regularPrice;

    @Before
    public void setUp() {
        regularPrice = new RegularPrice();
    }

    @Test
    public void test_get_amount_days_rented_less_than_or_equal_to_2() {
        assertEquals(2.0, regularPrice.getAmount(1), 0.01);
        assertEquals(2.0, regularPrice.getAmount(2), 0.01);
    }

    @Test
    public void test_get_amount_days_rented_greater_than_2() {
        assertEquals(3.5, regularPrice.getAmount(3), 0.01);
        assertEquals(6.5, regularPrice.getAmount(5), 0.01);
    }

    @Test
    public void test_get_frequent_renter_points() {
        assertEquals(1, regularPrice.getFrequentRenterPoints(1));
        assertEquals(1, regularPrice.getFrequentRenterPoints(5));
    }
}
