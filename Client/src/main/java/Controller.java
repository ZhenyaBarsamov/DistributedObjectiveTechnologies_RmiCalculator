import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Controller {
    private final Validator validator;
    private final RpnConverter converter;
    private final Deque<Double> operands;

    public Controller() {
        validator = new Validator();
        converter = new RpnConverter();
        operands = new ArrayDeque<>();
    }
    
    public void readExpressionAndCalculate() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для завершения работы введите 0");
        String expression;

        do {
            System.out.print("Введите выражение: ");
            expression = scanner.nextLine();

            while ( !validator.checkValidity(expression) ) {
                System.out.println("Некорректное выражение");
                System.out.print("Введите выражение: ");
                expression = scanner.nextLine();
            }

            if ( !"0".equals(expression) ) {
                // TODO: вызывать метод evaluate
                double result = 21;
                System.out.println("Результат: " + result);
            }

        }  while ( !"0".equals(expression) );

        System.out.println("Завершаем работу");
    }

    private double evaluate(String expression) throws Exception {
        Deque<Token> postfixExpression = converter.getExpressionRpn(expression);

        while ( !postfixExpression.isEmpty() ) {
            Token token = postfixExpression.poll();

            if ( !token.isOperation() ) {
                operands.push( token.getOperand() );
            }
            else {
                executeOperator( token.getOperation() );
            }
        }

        double result = operands.pop();
        if ( !operands.isEmpty() )
            throw new Exception("Ошибка при вычислении");

        return result;
    }

    private void executeOperator(char operator) {
        double b = operands.pop();
        double a = operands.pop();
        // TODO: заменить несуществующие методы на вызов сервисов
        switch (operator) {
            case '+': {
                operands.push(plus(a, b));
                break;
            }
            case '-': {
                operands.push(minus(a, b));
                break;
            }
            case '*': {
                operands.push(multiply(a, b));
                break;
            }
            case '/': {
                operands.push(divide(a, b));
                break;
            }
        }
    }
}
