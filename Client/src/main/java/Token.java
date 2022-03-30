public class Token {
    private final boolean isOperation;
    private char operation;
    private double operand;

    public Token(char operation) {
        isOperation = true;
        this.operation = operation;
    }

    public Token(double operand) {
        isOperation = false;
        this.operand = operand;
    }

    public boolean isOperation() {
        return isOperation;
    }

    public char getOperation() {
        return operation;
    }

    public double getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return isOperation() ?
                Character.toString(operation) :
                Double.toString(operand);
    }
}
