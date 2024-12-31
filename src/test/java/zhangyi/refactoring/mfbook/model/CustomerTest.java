package zhangyi.refactoring.mfbook.model;

import org.junit.jupiter.api.Test;
import zhangyi.refactoring.mfbook.view.CustomerFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    public void should_calculate_total_amount() {
        Customer customer = CustomerFixture.createCustomerWithThreeRentals();

        double totalAmount = customer.getTotalAmount();

        assertThat(totalAmount).isEqualTo(14.0d);
    }

    @Test
    public void should_calculate_total_points() {
        Customer customer = CustomerFixture.createCustomerWithThreeRentals();

        int totalPoints = customer.getTotalPoints();

        assertThat(totalPoints).isEqualTo(4);
    }
}