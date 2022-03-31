import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

// Класс Преобразователя - предназначен для преобразования выражения в виде строки
// в обратную польскую запись в виде очереди токенов выражения.
// Для получения токенов выражения используется класс LexicalAnalyzer.
public class RpnConverter {

    private int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '~':
                return 3;
        }
        return -1;
    }

    private String revealUnaryMinus(String expression) {
        return expression.replaceAll("(?x)" +
                        "(?<= (?: ^ | [-+*/\\(] ) )" +    // перед минусом стоит оператор, или открывающая скобка, или это начало строки
                        "   ( - )" +
                        "(?= (?: \\d+ (?:\\.\\d+)? | (?: \\( ) ) )", // после минуса стоит число
                "~"); // меняем на тильду
    }

    private Deque<Token> infixToPostfix(String expression) throws Exception
    {
        expression = revealUnaryMinus(expression);
        Deque<Token> postfixQueue = new ArrayDeque<>();
        LexicalAnalyzer lexer = new LexicalAnalyzer(expression);
        Token token = lexer.nextToken();
        Deque<Character> operationStack = new ArrayDeque<>();

        while ( token!= null )
        {
            if (!token.isOperation() )
                postfixQueue.offer(token);
            else if (token.getOperation() == '(')
                operationStack.push(token.getOperation());
            else if (token.getOperation() == ')')
            {
                while (!operationStack.isEmpty() && operationStack.peek() != '(')
                    postfixQueue.offer(new Token(operationStack.pop()));
                operationStack.pop();
            }
            else
            {
                while (!operationStack.isEmpty() && Prec(token.getOperation()) <= Prec(operationStack.peek())) {
                    postfixQueue.offer(new Token(operationStack.pop()));
                }
                operationStack.push(token.getOperation());
            }

            token = lexer.nextToken();
        }

        while (!operationStack.isEmpty()){
            postfixQueue.offer(new Token(operationStack.pop()));
        }
        return postfixQueue;
    }

    // Преобразовать выражение в обратную польскую запись и вернуть её в виде очереди токенов выражения
    public Deque<Token> getExpressionRpn(String expression) throws Exception {
        return infixToPostfix(expression);
    }
}
