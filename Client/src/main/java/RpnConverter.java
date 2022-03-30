import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

// Класс Преобразователя - предназначен для преобразования выражения в виде строки
// в обратную польскую запись в виде стека токенов выражения.
// Для получения токенов выражения используется класс LexicalAnalyzer.
public class RpnConverter {
    private static LexicalAnalyzer lexer;
    
    static int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }

    static Deque<Token> infixToPostfix(String exp) throws Exception
    {
        Deque<Token> tokens = new ArrayDeque<>();
        lexer = new LexicalAnalyzer(exp);
        Token token = lexer.nextToken();
        Stack<Character> stack = new Stack<>();

        while ( token!= null )
        {
            System.out.println(token);
            if (!token.isOperation() )
                tokens.offer(token);
            else if (token.getOperation() == '(')
                stack.push(token.getOperation());
            else if (token.getOperation() == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    tokens.offer(new Token(stack.pop()));
                stack.pop();
            }
            else
            {
                while (!stack.isEmpty() && Prec(token.getOperation())
                        <= Prec(stack.peek())){
                    tokens.offer(new Token(stack.pop()));
                }
                    stack.push(token.getOperation());
            }

            token = lexer.nextToken();
        }

        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return tokens;
            tokens.offer(new Token(stack.pop()));
        }
        return tokens;
    }

    // Преобразовать выражение в обратную польскую запись и вернуть её в виде стека токенов выражения
    public Deque<Token> getExpressionRpn(String expression) throws Exception {
        Deque<Token> result = infixToPostfix(expression);
        return result;
    }
}
