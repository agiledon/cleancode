package zhangyi.refactoring.mfbook.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PriceCodeTest {
    @Test
    public void should_return_1_for_children_price_code() {
        PriceCode priceCode = new ChildrenPriceCode();

        int points = priceCode.pointsFor(3);

        assertThat(points).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("priceCodeFactory")
    public void should_create_correct_PriceCode_given_price_code(int priceCode, Class<PriceCode> priceCodeClass) {
        PriceCode pCode = PriceCode.getPriceCode(priceCode);
        assertThat(pCode).isInstanceOf(priceCodeClass);
    }

    private static Stream<Arguments> priceCodeFactory() {
        return Stream.of(
                arguments(0, RegularPriceCode.class),
                arguments(1, NewReleasePriceCode.class),
                arguments(2, ChildrenPriceCode.class),
                arguments(3, DefaultPriceCode.class)
                );
    }
}