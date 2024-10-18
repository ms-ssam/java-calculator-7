package calculator;


import calculator.analyzer.DelimiterAnalyzer;
import calculator.extractor.NumberExtractor;
import calculator.myCalculator.AddCalculator;
import calculator.util.DelimiterRegexGenerator;
import calculator.validator.InputValidator;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        DelimiterAnalyzer delimiterAnalyzer = new DelimiterAnalyzer();
        DelimiterRegexGenerator delimiterRegexGenerator = new DelimiterRegexGenerator(delimiterAnalyzer);
        NumberExtractor numberExtractor = new NumberExtractor(delimiterRegexGenerator, delimiterAnalyzer);

        // 사용자 입력
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        // 유효하지 않은 입력인 경우 예외 발생
        InputValidator inputValidator = new InputValidator(delimiterRegexGenerator, delimiterAnalyzer);
        if(!inputValidator.isValid(input)) {
            throw new IllegalArgumentException();
        }

        // 문자열 덧셈 계산기로 계산
        AddCalculator calculator = new AddCalculator(new NumberExtractor(delimiterRegexGenerator, delimiterAnalyzer));
        System.out.println("결과 : " + calculator.addNumbersInString(input));
    }
}
