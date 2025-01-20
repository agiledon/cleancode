package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChildrenPriceTest {
    private ChildrenPrice childrenPrice;

    @Before
    public void setUp() {
        childrenPrice = new ChildrenPrice();
    }

    @Test
    public void test_get_amount_days_rented_less_than_or_equal_to_3() {
        assertEquals(1.5, childrenPrice.getAmount(1), 0.01);
        assertEquals(1.5, childrenPrice.getAmount(3), 0.01);
    }

    @Test
    public void test_get_amount_days_rented_greater_than_3() {
        assertEquals(3.0, childrenPrice.getAmount(4), 0.01);
        assertEquals(4.5, childrenPrice.getAmount(5), 0.01);
    }

    @Test
    public void test_get_frequent_renter_points() {
        assertEquals(1, childrenPrice.getFrequentRenterPoints(1));
        assertEquals(1, childrenPrice.getFrequentRenterPoints(5));
    }
}
