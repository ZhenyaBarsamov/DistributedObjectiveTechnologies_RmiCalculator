import java.util.List;

// Класс Лексического анализатора, предназначенный для разбиения выражения на токены: операции и операнды
public class LexicalAnalyzer {
    // Разбираемое выражение в виде строки
    private String expression;

    // Текущий индекс символа в разбираемом выражении
    private int currentCharIndex;

    // Конструктор
    public LexicalAnalyzer(String expression) {
        this.expression = expression;
        currentCharIndex = 0;
    }

    // Получить следующий токен выражения
    public Token getNextToken() throws Exception {
        throw new Exception("Не реализовано");
    }

    // Получть все токены выражения в виде списка
    public List<Token> getTokensArrayList() throws Exception {
        throw new Exception("Не реализовано");
    }
}
