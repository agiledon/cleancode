package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ChildrenPriceStrategy类的单元测试
 * 测试儿童片的价格计算和积分计算逻辑
 */
public class ChildrenPriceStrategyTest {

    private ChildrenPriceStrategy strategy;

    @Before
    public void setUp() {
        strategy = new ChildrenPriceStrategy();
    }

    @Test
    public void should_return_zero_amount_for_zero_days() {
        // Given
        int daysRented = 0;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(0.0, amount, 0.001); // Zero days should return 0
    }

    @Test
    public void should_return_zero_amount_for_negative_days() {
        // Given
        int daysRented = -1;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(0.0, amount, 0.001); // Negative days should return 0
    }

    @Test
    public void should_return_base_amount_for_one_day() {
        // Given
        int daysRented = 1;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(1.5, amount, 0.001); // One day should return base amount
    }

    @Test
    public void should_return_base_amount_for_two_days() {
        // Given
        int daysRented = 2;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(1.5, amount, 0.001); // Two days should return base amount
    }

    @Test
    public void should_return_base_amount_for_three_days() {
        // Given
        int daysRented = 3;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(1.5, amount, 0.001); // Three days should return base amount
    }

    @Test
    public void should_return_base_amount_plus_extra_for_four_days() {
        // Given
        int daysRented = 4;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(3.0, amount, 0.001); // Four days: 1.5 + 1.5 = 3.0
    }

    @Test
    public void should_return_base_amount_plus_extra_for_five_days() {
        // Given
        int daysRented = 5;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(4.5, amount, 0.001); // Five days: 1.5 + 2*1.5 = 4.5
    }

    @Test
    public void should_return_zero_points_for_zero_days() {
        // Given
        int daysRented = 0;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(0, points); // Zero days should return 0 points
    }

    @Test
    public void should_return_zero_points_for_negative_days() {
        // Given
        int daysRented = -1;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(0, points); // Negative days should return 0 points
    }

    @Test
    public void should_return_one_point_for_positive_days() {
        // Given
        int daysRented = 1;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(1, points); // Always return 1 point for children movies
    }

    @Test
    public void should_return_one_point_for_multiple_days() {
        // Given
        int daysRented = 5;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(1, points); // Always return 1 point for children movies
    }

    @Test
    public void should_return_base_amount_for_ten_days_rental() {
        // Given
        int daysRented = 10;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(12.0, amount, 0.001); // 1.5 + (10-3) * 1.5 = 12.0
    }

    @Test
    public void should_return_consistent_amount_for_same_input() {
        // Given
        int daysRented = 6;

        // When
        double amount1 = strategy.getAmount(daysRented);
        double amount2 = strategy.getAmount(daysRented);

        // Then
        assertEquals(amount1, amount2, 0.001);
    }

    @Test
    public void should_return_consistent_points_for_same_input() {
        // Given
        int daysRented = 6;

        // When
        int points1 = strategy.getFrequentRenterPoints(daysRented);
        int points2 = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(points1, points2);
    }

    @Test
    public void should_have_constant_amount_for_first_three_days() {
        // Given
        int days1 = 1;
        int days2 = 2;
        int days3 = 3;

        // When
        double amount1 = strategy.getAmount(days1);
        double amount2 = strategy.getAmount(days2);
        double amount3 = strategy.getAmount(days3);

        // Then
        assertEquals(1.5, amount1, 0.001);
        assertEquals(1.5, amount2, 0.001);
        assertEquals(1.5, amount3, 0.001);
        assertTrue(amount1 == amount2 && amount2 == amount3); // Constant amount
    }

    @Test
    public void should_have_linear_increase_after_three_days() {
        // Given
        int days4 = 4;
        int days5 = 5;
        int days6 = 6;

        // When
        double amount4 = strategy.getAmount(days4);
        double amount5 = strategy.getAmount(days5);
        double amount6 = strategy.getAmount(days6);

        // Then
        assertEquals(3.0, amount4, 0.001);
        assertEquals(4.5, amount5, 0.001);
        assertEquals(6.0, amount6, 0.001);
        assertTrue(amount5 - amount4 == amount6 - amount5); // Linear increase after 3 days
    }
}