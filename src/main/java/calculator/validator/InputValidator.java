package calculator.validator;

import calculator.analyzer.DelimiterAnalyzer;
import calculator.util.DelimiterRegexGenerator;

import java.util.Arrays;
import java.util.List;

public class InputValidator {
    private DelimiterRegexGenerator delimiterRegexGenerator;
    private DelimiterAnalyzer delimiterAnalyzer;

    public InputValidator(DelimiterRegexGenerator delimiterRegexGenerator, DelimiterAnalyzer delimiterAnalyzer) {
        this.delimiterRegexGenerator = delimiterRegexGenerator;
        this.delimiterAnalyzer = delimiterAnalyzer;
    }

    // ",,,1,2::3" 이런 식이면? 첫 문자 숫자 아니고, idx 0:2, 3:5가 //, \n 아니고 구분자 목록에 없는
    // 문자들이 있으면 false?
    // "///;\n1;2;;3" 이런 식이면?
    // ",//;\n1;2,3:7" 이런 식이면?

    // 사용자 입력이 잘못된 입력인지 검사
    public boolean isValid(String input) {
        // 아무 입력 없는 경우
        if(input.equals(""))
            return true;

        // 사용자 입력 정제
        String trimmedInput = input;
        if(delimiterAnalyzer.isContainCustomDelimiter(input))
            trimmedInput = input.substring(5, input.length());

        // 구분자 정규식을 통해 입력 split
        String regex = delimiterRegexGenerator.generateDelimiterRegex(input);
        List<String> strNumList = Arrays.stream(trimmedInput.split(regex)).toList();

        // 빈 문자열 제거
        strNumList = strNumList.stream().filter(i -> !i.equals("")).toList();

        // 숫자가 아닌 문자 포함하는 요소가 있는 경우 잘못된 입력
        // 구분자 이외의 문자가 포함되는 경우이기 때문에 오류 (음수를 입력하는 경우도 포함됨)
        for(String strNum : strNumList) {  // 시간 복잡도?
            for(char c : strNum.toCharArray()) {;
                if(!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
