import java.util.Stack;

// Класс Преобразователя - предназначен для преобразования выражения в виде строки
// в обратную польскую запись в виде стека токенов выражения.
// Для получения токенов выражения используется класс LexicalAnalyzer.
public class RpnConverter {
    private LexicalAnalyzer lexer;
    
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
      
    static String infixToPostfix(String exp)
    {
        String result = new String("");
         
        Stack<Character> stack = new Stack<>();
         
        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);
             

            if (Character.isDigit(c)||c=='.')
                result += c;
              

            else if (c == '(')
                stack.push(c);
             

            else if (c == ')')
            {
                while (!stack.isEmpty() &&
                        stack.peek() != '(')
                    result +=  " "+stack.pop();
                 
                    stack.pop();
            }
            else 
            {
                while (!stack.isEmpty() && Prec(c)
                         <= Prec(stack.peek())){
                   
                    result += " "+stack.pop();
             }
             if(!result.isEmpty())
             result +=' ';
                stack.push(c);
            }
      
        }
      
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += " "+stack.pop();
         }
        return result;
    }

    // Преобразовать выражение в обратную польскую запись и вернуть её в виде стека токенов выражения
    public Stack<Token> getExpressionRpn(String expression) throws Exception {
	    String resExp = infixToPostfix(expression);
        lexer = new LexicalAnalyzer(resExp);
        return null; //lexer.allTokens();
    }
}
