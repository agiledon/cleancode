package zhangyi.utpractice.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StringCalculatorTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void should_add_all_numbers_with_string_format(String numbers, int expectedSum) {
        int actualSum = StringCalculator.add(numbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(null, 0),
                arguments("", 0),
                arguments("    ", 0),
                arguments("\n\n\b\r", 0),
                arguments("1", 1),
                arguments("1,2", 3),
                arguments("1,2,3", 6),
                arguments("1,2,1000", 3),
                arguments("1,2,1001,", 3),
                arguments("1, 2, 999", 1002)
        );
    }
}