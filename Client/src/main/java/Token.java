// Класс Токена - элемента выражения. Токен может быть либо операцией, либо операндом.
// Операция представлена в виде символа, операнд - числом double
public class Token {
    // Флаг, показывающий, является ли данный токен операцией
    private boolean isOperation;
    public boolean isOperation() {
        return isOperation;
    }

    // Операция (поле актуально, если токен является операцией)
    private char operation;
    public char getOperation() throws Exception {
        if (!isOperation)
            throw new Exception("Токен не является операцией");
        return operation;
    }

    // Операнд (поле актуально, если токен является операндом)
    private double operand;
    public double getOperand() throws Exception {
        if (isOperation)
            throw new Exception("Токен не является операндом");
        return operand;
    }

    // Конструктор для токена-операции
    public Token(char operation) {
        isOperation = true;
        this.operation = operation;
    }

    // Конструктор для токена-операнда
    public Token(double operand) {
        isOperation = false;
        this.operand = operand;
    }

    @Override
    public String toString() {
        return isOperation() ?
                Character.toString(operation) :
                Double.toString(operand);
    }
}
