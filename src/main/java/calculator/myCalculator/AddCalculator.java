package calculator.myCalculator;

import calculator.extractor.NumberExtractor;

import java.util.List;
import java.util.Optional;

public class AddCalculator {
    private NumberExtractor numberExtractor;

    public AddCalculator(NumberExtractor numberExtractor) {
        this.numberExtractor = numberExtractor;
    }

    public Integer addNumbersInString(String input) {
        List<Integer> numList = numberExtractor.extractNumber(input);
        Integer result = numList.stream().reduce((a, b) -> a+b).orElse(0);

        return result;
    }
}
