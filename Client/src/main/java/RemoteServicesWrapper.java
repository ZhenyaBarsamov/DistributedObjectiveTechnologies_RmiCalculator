import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Обёртка для удалённого вызова методов сервисов операций
public class RemoteServicesWrapper {
    // Вспоминаем уникальные имена каждого удалённого объекта
    private static final String UNIQUE_BINDING_NAME_PLUS = "server.plus_service";
    private static final String UNIQUE_BINDING_NAME_MINUS = "server.minus_service";
    private static final String UNIQUE_BINDING_NAME_MULTIPLY = "server.multiply_service";
    private static final String UNIQUE_BINDING_NAME_DIVISION ="server.division_service";

    // Переменные, в которых будем держать ссылки на удалённые объекты
    private final OperationService plusService;
    private final OperationService minusService;
    private final OperationService multiplyService;
    private final OperationService divisionService;

    // Создать объект-обёртку для удалённых операций.
    // port - номер порта, на котором запущен реестр удалённых объектов
    public RemoteServicesWrapper(int port) throws RemoteException, NotBoundException {
        // Получаем реестр удалённых объектов с порта, на котором он был запущен
        final Registry registry = LocateRegistry.getRegistry(port);
        // Получаем из реестра нужные объекты сервисов по их уникальным именам
        plusService = (OperationService) registry.lookup(UNIQUE_BINDING_NAME_PLUS);
        minusService = (OperationService) registry.lookup(UNIQUE_BINDING_NAME_MINUS);
        multiplyService = (OperationService) registry.lookup(UNIQUE_BINDING_NAME_MULTIPLY);
        divisionService = (OperationService) registry.lookup(UNIQUE_BINDING_NAME_DIVISION);
    }

    // Создать объект-обёртку для удалённых операций с портом по умолчанию: 2732
    public RemoteServicesWrapper() throws RemoteException, NotBoundException {
        this(2732);
    }

    // Выполнить операцию сложения для заданных операндов
    public double plus(double op1, double op2) throws RemoteException {
        return plusService.applyOperation(op1, op2);
    }

    // Выполнить операцию вычитания для заданных операндов
    public double minus(double op1, double op2) throws RemoteException {
        return minusService.applyOperation(op1, op2);
    }

    // Выполнить операцию умножения для заданных операндов
    public double multiply(double op1, double op2) throws RemoteException {
        return multiplyService.applyOperation(op1, op2);
    }

    // Выполнить операцию деления для заданных операндов
    public double division(double op1, double op2) throws RemoteException {
        return divisionService.applyOperation(op1, op2);
    }
}
