package zhangyi.utpractice.string;

import java.util.Objects;

public class StringCalculator {
    public static int add(String numbers) {
        int result = 0;

        if (Objects.isNull(numbers)) {
            return result;
        }

        if (numbers.trim().isEmpty())
            return result;

        String[] parts = numbers.split(",");

        for (String part: parts) {
            int integer = Integer.parseInt(part.trim());

            if (integer >= 1000)
                continue;

            result += integer;
        }
        return result;
    }
}