import java.util.Stack;

// Класс Преобразователя - предназначен для преобразования выражения в виде строки
// в обратную польскую запись в виде стека токенов выражения.
// Для получения токенов выражения используется класс LexicalAnalyzer.
public class RpnConverter {
    private LexicalAnalyzer lexer;

    // Преобразовать выражение в обратную польскую запись и вернуть её в виде стека токенов выражения
    public Stack<Token> getExpressionRpn(String expression) throws Exception {
        lexer = new LexicalAnalyzer(expression);
        throw new Exception("Не реализовано");
    }
}
