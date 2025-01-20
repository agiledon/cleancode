package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullPriceTest {
    private NullPrice nullPrice;

    @Before
    public void setUp() {
        nullPrice = new NullPrice();
    }

    @Test
    public void test_get_amount() {
        assertEquals(0.0, nullPrice.getAmount(1), 0.01);
        assertEquals(0.0, nullPrice.getAmount(5), 0.01);
    }

    @Test
    public void test_get_frequent_renter_points() {
        assertEquals(0, nullPrice.getFrequentRenterPoints(1));
        assertEquals(0, nullPrice.getFrequentRenterPoints(5));
    }
}
