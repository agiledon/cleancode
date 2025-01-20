package zhangyi.refactoring.mfbook;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceTest {
    @Test
    public void test_create_price_regular() {
        Price price = Price.createPrice(Movie.REGULAR);
        assertTrue(price instanceof RegularPrice);
    }

    @Test
    public void test_create_price_new_release() {
        Price price = Price.createPrice(Movie.NEW_RELEASE);
        assertTrue(price instanceof NewReleasePrice);
    }

    @Test
    public void test_create_price_children() {
        Price price = Price.createPrice(Movie.CHILDREN);
        assertTrue(price instanceof ChildrenPrice);
    }

    @Test
    public void test_create_price_unknown() {
        Price price = Price.createPrice(999); // 假设999是一个未知的priceCode
        assertTrue(price instanceof NullPrice);
    }
}
