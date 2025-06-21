package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * NewReleasePriceStrategy类的单元测试
 * 测试新片的价格计算和积分计算逻辑
 */
public class NewReleasePriceStrategyTest {

    private NewReleasePriceStrategy strategy;

    @Before
    public void setUp() {
        strategy = new NewReleasePriceStrategy();
    }

    @Test
    public void should_return_three_times_days_for_one_day_rental() {
        // Given
        int daysRented = 1;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(3.0, amount, 0.001); // 1 * 3 = 3.0
    }

    @Test
    public void should_return_three_times_days_for_two_days_rental() {
        // Given
        int daysRented = 2;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(6.0, amount, 0.001); // 2 * 3 = 6.0
    }

    @Test
    public void should_return_three_times_days_for_five_days_rental() {
        // Given
        int daysRented = 5;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(15.0, amount, 0.001); // 5 * 3 = 15.0
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
    public void should_return_three_amount_for_one_day() {
        // Given
        int daysRented = 1;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(3.0, amount, 0.001); // One day: 1 * 3 = 3.0
    }

    @Test
    public void should_return_six_amount_for_two_days() {
        // Given
        int daysRented = 2;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(6.0, amount, 0.001); // Two days: 2 * 3 = 6.0
    }

    @Test
    public void should_return_nine_amount_for_three_days() {
        // Given
        int daysRented = 3;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(9.0, amount, 0.001); // Three days: 3 * 3 = 9.0
    }

    @Test
    public void should_return_fifteen_amount_for_five_days() {
        // Given
        int daysRented = 5;

        // When
        double amount = strategy.getAmount(daysRented);

        // Then
        assertEquals(15.0, amount, 0.001); // Five days: 5 * 3 = 15.0
    }

    @Test
    public void should_return_one_point_for_one_day_rental() {
        // Given
        int daysRented = 1;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(1, points);
    }

    @Test
    public void should_return_two_points_for_two_days_rental() {
        // Given
        int daysRented = 2;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // 1 + 1 (bonus for > 1 day)
    }

    @Test
    public void should_return_two_points_for_three_days_rental() {
        // Given
        int daysRented = 3;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // 1 + 1 (bonus for > 1 day)
    }

    @Test
    public void should_return_two_points_for_ten_days_rental() {
        // Given
        int daysRented = 10;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // 1 + 1 (bonus for > 1 day)
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
    public void should_return_one_point_for_one_day() {
        // Given
        int daysRented = 1;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(1, points); // One day should return 1 point
    }

    @Test
    public void should_return_two_points_for_two_days() {
        // Given
        int daysRented = 2;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // Two days should return 2 points
    }

    @Test
    public void should_return_two_points_for_three_days() {
        // Given
        int daysRented = 3;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // Three days should return 2 points
    }

    @Test
    public void should_return_two_points_for_five_days() {
        // Given
        int daysRented = 5;

        // When
        int points = strategy.getFrequentRenterPoints(daysRented);

        // Then
        assertEquals(2, points); // Five days should return 2 points
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

    @Test
    public void should_have_linear_amount_increase() {
        // Given
        int days1 = 1;
        int days2 = 2;
        int days3 = 3;

        // When
        double amount1 = strategy.getAmount(days1);
        double amount2 = strategy.getAmount(days2);
        double amount3 = strategy.getAmount(days3);

        // Then
        assertEquals(3.0, amount1, 0.001);
        assertEquals(6.0, amount2, 0.001);
        assertEquals(9.0, amount3, 0.001);
        assertTrue(amount2 - amount1 == amount3 - amount2); // Linear increase
    }
}