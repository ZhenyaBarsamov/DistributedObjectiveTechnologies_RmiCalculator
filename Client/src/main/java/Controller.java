import java.util.Stack;

// Класс Контроллера - основного класса клиентского приложения
public class Controller {
    // Введённое выражение в виде строки
    private String expression;
    // Разобранное выражение в ОПЗ в виде стека токенов
    private Stack<Token> expressionStack;

    private Validator validator;
    private RpnConverter converter;

    // Считывание и вычисление выражения.
    // Здесь выражение считывается из консоли, затем проверяется Валидатором,
    // затем преобразуется в стек ОПЗ Преобразователем, и вычисляется с помощью сервисов.
    // Вычисление по стеку, скорее всего, уйдёт в дополнительный приватный метод
    public void readExpressionAndCalculate() throws Exception {
        throw new Exception("Не реализовано");
    }

    // Конструктор
    public Controller() {
        validator = new Validator();
        converter = new RpnConverter();
    }
}
