import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Controller {
    private final Validator validator;
    private final RpnConverter converter;
    private final Deque<Double> operands;
    private final RemoteServicesWrapper services;

    public Controller() throws NotBoundException, RemoteException {
        validator = new Validator();
        converter = new RpnConverter();
        operands = new ArrayDeque<>();
        services = new RemoteServicesWrapper();
    }

    public void readExpressionAndCalculate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для завершения работы введите 0");
        String expression;

        do {
            System.out.print("Введите выражение: ");
            expression = scanner.nextLine();

            if (expression.endsWith("clear")) {
                continue;
            }

            while (  !validator.checkValidity( expression.trim().replace(" ", "") )  ) {
                System.out.println("Некорректное выражение");
                System.out.print("Введите выражение: ");
                expression = scanner.nextLine();
            }

            if ( !"0".equals(expression) ) {
                try {
                    double result = evaluate(expression);
                    System.out.println("Результат: " + result);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
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

        if ( operands.isEmpty() ) {
            throw new Exception("Ошибка при вычислении");
        }
        double result = operands.pop();
        if ( !operands.isEmpty() ) {
            operands.clear();
            throw new Exception("Ошибка при вычислении");
        }

        return result;
    }

    private void executeOperator(char operator) throws Exception {
        switch (operator) {
            case '+': {
                double b = operands.pop();
                double a = operands.pop();
                operands.push(services.plus(a, b));
                break;
            }
            case '-': {
                double b = operands.pop();
                double a = operands.pop();
                operands.push(services.minus(a, b));
                break;
            }
            case '*': {
                double b = operands.pop();
                double a = operands.pop();
                operands.push(services.multiply(a, b));
                break;
            }
            case '/': {
                double b = operands.pop();
                double a = operands.pop();
                if (b == 0) {
                    operands.clear();
                    throw new Exception("Деление на ноль");
                }
                operands.push(services.division(a, b));
                break;
            }
            case '~': {
                if (operands.isEmpty()) {
                    throw new Exception("Неверный формат выражения");
                }

                double b = operands.pop();
                operands.push(-b);
                break;
            }
        }
    }
}
