import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class Validator {

    private boolean checkParenthesis(String expression) {
        Deque<Character> parenthesis = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char cur = expression.charAt(i);

            if (cur == '(') {
                parenthesis.push(cur);

            } else if (cur == ')') {

                if (parenthesis.isEmpty())
                    return false;

                parenthesis.pop();
            }
        }

        return parenthesis.isEmpty();
    }

    /**
     * Проверяет, является ли строка с математическим выражением, корректной. Использует регулярные выражения и алгоритм
     * проверки баланса скобок в выражении.
     * @param expression Математическое выражение
     * @return Флаг, означающий корректно выражение или нет
     */
    public boolean checkValidity(String expression) {
        Pattern pattern = Pattern.compile("(?x) ^" +
                "-?" +
                "(?> (?: \\( -? )* (?> -? \\d+ (?:\\.\\d+)? ) ( \\) )*   )" +
                "(?>(?:" +
                "   [-+*/]" +
                "   (?> (?: \\( -? )* (?> -? \\d+ (?:\\.\\d+)? ) ( \\) )*   )" +
                ")*   )" +
                "$");

        return pattern.matcher(expression).matches() &&
                checkParenthesis(expression);
    }
}
