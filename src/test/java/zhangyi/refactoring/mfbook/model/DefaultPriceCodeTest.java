package zhangyi.refactoring.mfbook.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultPriceCodeTest {
    @Test
    public void should_return_0_as_default_amount() {
        DefaultPriceCode priceCode = new DefaultPriceCode();

        double amount = priceCode.amountFor(3);

        assertThat(amount).isEqualTo(0);
    }

    @Test
    public void should_return_0_as_default_point() {
        DefaultPriceCode priceCode = new DefaultPriceCode();

        int amount = priceCode.pointsFor(3);

        assertThat(amount).isEqualTo(0);
    }

}