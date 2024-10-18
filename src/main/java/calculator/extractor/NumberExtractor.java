package calculator.extractor;

import calculator.analyzer.DelimiterAnalyzer;
import calculator.util.DelimiterRegexGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberExtractor {
    private DelimiterRegexGenerator delimiterRegexGenerator;
    private DelimiterAnalyzer delimiterAnalyzer;

    public NumberExtractor(DelimiterRegexGenerator delimiterRegexGenerator, DelimiterAnalyzer delimiterAnalyzer) {
        this.delimiterRegexGenerator = delimiterRegexGenerator;
        this.delimiterAnalyzer = delimiterAnalyzer;
    }

    // 사용자 입력으로부터 숫자만 추출한 리스트 반환
    public List<Integer> extractNumber(String input) {
        // 사용자 입력 정제
        String trimmedInput = input;
        if(delimiterAnalyzer.isContainCustomDelimiter(input))
            trimmedInput = input.substring(5, input.length());

        String regex = delimiterRegexGenerator.generateDelimiterRegex(input);

        // 구분자들로 split
        List<String> strNumberList = new ArrayList<>();
        strNumberList = Arrays.stream(trimmedInput.split(regex)).toList();

        // 빈 문자열 제거 ("1,,,2,3" 같이 구분자가 연속해서 나온 입력의 경우 빈 문자열 발생)
        strNumberList = strNumberList.stream().filter(i -> !i.equals("")).toList();

        // 문자형 숫자들을 정수로 변환
        List<Integer> numList = strNumberList.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        return numList;
    }
}
