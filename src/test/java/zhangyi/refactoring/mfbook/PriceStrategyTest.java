package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PriceStrategy接口的单元测试
 * 测试接口的基本契约和所有实现类的共同行为
 */
public class PriceStrategyTest {

    private PriceStrategy regularStrategy;
    private PriceStrategy newReleaseStrategy;
    private PriceStrategy childrenStrategy;

    @Before
    public void setUp() {
        regularStrategy = new RegularPriceStrategy();
        newReleaseStrategy = new NewReleasePriceStrategy();
        childrenStrategy = new ChildrenPriceStrategy();
    }

    @Test
    public void should_return_positive_amount_for_positive_days() {
        // Given
        int daysRented = 5;

        // When & Then
        assertTrue(regularStrategy.getAmount(daysRented) > 0);
        assertTrue(newReleaseStrategy.getAmount(daysRented) > 0);
        assertTrue(childrenStrategy.getAmount(daysRented) > 0);
    }

    @Test
    public void should_return_zero_amount_for_zero_or_negative_days() {
        // Given
        int daysRented = 0;

        // When & Then
        assertEquals(0.0, regularStrategy.getAmount(daysRented), 0.001); // Zero days should return 0
        assertEquals(0.0, newReleaseStrategy.getAmount(daysRented), 0.001);
        assertEquals(0.0, childrenStrategy.getAmount(daysRented), 0.001); // Zero days should return 0

        // Test negative days
        daysRented = -1;
        assertEquals(0.0, regularStrategy.getAmount(daysRented), 0.001); // Negative days should return 0
        assertEquals(0.0, newReleaseStrategy.getAmount(daysRented), 0.001);
        assertEquals(0.0, childrenStrategy.getAmount(daysRented), 0.001); // Negative days should return 0
    }

    @Test
    public void should_return_positive_points_for_positive_days() {
        // Given
        int daysRented = 5;

        // When & Then
        assertTrue(regularStrategy.getFrequentRenterPoints(daysRented) > 0);
        assertTrue(newReleaseStrategy.getFrequentRenterPoints(daysRented) > 0);
        assertTrue(childrenStrategy.getFrequentRenterPoints(daysRented) > 0);
    }

    @Test
    public void should_return_zero_points_for_zero_or_negative_days() {
        // Given
        int daysRented = 0;

        // When & Then
        assertEquals(0, regularStrategy.getFrequentRenterPoints(daysRented)); // Zero days should return 0 points
        assertEquals(0, newReleaseStrategy.getFrequentRenterPoints(daysRented));
        assertEquals(0, childrenStrategy.getFrequentRenterPoints(daysRented)); // Zero days should return 0 points

        // Test negative days
        daysRented = -1;
        assertEquals(0, regularStrategy.getFrequentRenterPoints(daysRented)); // Negative days should return 0 points
        assertEquals(0, newReleaseStrategy.getFrequentRenterPoints(daysRented));
        assertEquals(0, childrenStrategy.getFrequentRenterPoints(daysRented)); // Negative days should return 0 points
    }

    @Test
    public void should_return_at_least_one_point_for_positive_rental() {
        // Given
        int daysRented = 1;

        // When & Then
        assertTrue(regularStrategy.getFrequentRenterPoints(daysRented) >= 1);
        assertTrue(newReleaseStrategy.getFrequentRenterPoints(daysRented) >= 1);
        assertTrue(childrenStrategy.getFrequentRenterPoints(daysRented) >= 1);
    }

    @Test
    public void should_handle_negative_days_gracefully() {
        // Given
        int daysRented = -1;

        // When & Then
        try {
            regularStrategy.getAmount(daysRented);
            newReleaseStrategy.getAmount(daysRented);
            childrenStrategy.getAmount(daysRented);
            regularStrategy.getFrequentRenterPoints(daysRented);
            newReleaseStrategy.getFrequentRenterPoints(daysRented);
            childrenStrategy.getFrequentRenterPoints(daysRented);
        } catch (Exception e) {
            fail("Should not throw exception for negative days");
        }
    }

    @Test
    public void should_return_consistent_results_for_same_input() {
        // Given
        int daysRented = 3;

        // When
        double amount1 = regularStrategy.getAmount(daysRented);
        double amount2 = regularStrategy.getAmount(daysRented);
        int points1 = regularStrategy.getFrequentRenterPoints(daysRented);
        int points2 = regularStrategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(amount1, amount2, 0.001);
        assertEquals(points1, points2);
    }
}