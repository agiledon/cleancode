package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * RegularPriceStrategy类的单元测试
 * 测试普通电影的价格计算和积分计算逻辑
 */
public class RegularPriceStrategyTest {

    private RegularPriceStrategy strategy;

    @Before
    public void setUp() {
        strategy = new RegularPriceStrategy();
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
        assertEquals(2.0, amount, 0.001); // One day should return base amount
    }

    @Test
    public void should_return_base_amount_for_two_days() {
        // Given
        int daysRented = 2;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(2.0, amount, 0.001); // Two days should return base amount
    }

    @Test
    public void should_return_base_amount_plus_extra_for_three_days() {
        // Given
        int daysRented = 3;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(3.5, amount, 0.001); // Three days: 2.0 + 1.5 = 3.5
    }

    @Test
    public void should_return_base_amount_plus_extra_for_five_days() {
        // Given
        int daysRented = 5;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(6.5, amount, 0.001); // Five days: 2.0 + 3*1.5 = 6.5
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
        assertEquals(1, points); // Always return 1 point for regular movies
    }

    @Test
    public void should_return_one_point_for_multiple_days() {
        // Given
        int daysRented = 5;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(1, points); // Always return 1 point for regular movies
    }

    @Test
    public void should_return_consistent_amount_for_same_input() {
        // Given
        int daysRented = 4;

        // When
        double amount1 = strategy.getAmount(daysRented);
        double amount2 = strategy.getAmount(daysRented);

        // Then
        assertEquals(amount1, amount2, 0.001);
    }

    @Test
    public void should_return_consistent_points_for_same_input() {
        // Given
        int daysRented = 4;

        // When
        int points1 = strategy.getFrequentRenterPoints(daysRented);
        int points2 = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(points1, points2);
    }
}