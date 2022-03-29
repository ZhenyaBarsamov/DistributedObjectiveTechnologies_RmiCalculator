import java.util.ArrayList;
import java.util.List;

// Класс Лексического анализатора, предназначенный для разбиения выражения на токены: операции и операнды
public class LexicalAnalyzer {
    private final StringBuilder valueBuffer;    // буфер для создания чисел
    private char[] expression;                  // строка выражения
    private int idx;                            // текущий индекс в строке

    public LexicalAnalyzer(String expression) {
        this.idx = 0;
        this.valueBuffer = new StringBuilder();
        // очищаем от пробелов и приводим к массиву, чтобы итерироваться
        this.expression = expression.trim()
                .replace(" ", "")
                .toCharArray();
    }

    /***
     * Сеттер для строки, которую необходимо разбить на токены
     * @param expression Строка String с математическим выражением
     */
    public void setExpression(String expression) {
        this.idx = 0;
        this.valueBuffer.setLength(0);
        this.expression = expression.trim()
                .replace(" ", "")
                .toCharArray();
    }

    /***
     * Метод, который возвращает один токен из исходной строки. При достижении конца строки возвращает null.
     * @return Объект класса Token или null, если достигнут конец строки
     * @throws Exception Исключение происходит, если в строке был встречен неожиданный символ
     */
    public Token nextToken() throws Exception {
        if (idx >= expression.length) {
            return null;
        }
        char current = expression[idx];
        Token token;

        if (isDigit(current)) {
            // если число, то собираем все числовые символы в буфер, пока не встретим нечисловой символ
            while ( idx < expression.length && isDigit(expression[idx]) ) {
                valueBuffer.append(expression[idx++]);
            }

            token = new Token(Double.parseDouble(valueBuffer.toString()));
            valueBuffer.setLength(0);
        }
        else if (isOperator(current) || isParenthesis(current)) {
            token = new Token(expression[idx++]);
        }
        else {
            throw new Exception("Неожиданный символ");
        }

        return token;
    }

    /***
     * Возвращает список всех токенов переданной строки. Под капотом вызывает метод nextToken в цикле,
     * пока не получит null
     * @return Объект класса ArrayList с объектами класса Token
     * @throws Exception Исключение происходит, если в строке был встречен неожиданный символ
     */
    public List<Token> allTokens() throws Exception {
        List<Token> tokens = new ArrayList<>();
        Token token;

        while ( (token = nextToken()) != null ) {
            tokens.add(token);
        }

        return tokens;
    }

    private boolean isDigit(char raw) {
        return Character.isDigit(raw) || raw == '.';
    }

    private boolean isOperator(char raw) {
        return "+-/*~".indexOf(raw) != -1;
    }

    private boolean isParenthesis(char raw) {
        return "()".indexOf(raw) != -1;
    }
}
