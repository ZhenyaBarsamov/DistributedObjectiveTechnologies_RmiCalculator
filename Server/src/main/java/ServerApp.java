import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ServerApp {
    // Определяем для каждого удалённого объекта уникальное имя
    public static final String UNIQUE_BINDING_NAME_PLUS = "server.plus_service";
    public static final String UNIQUE_BINDING_NAME_MINUS = "server.minus_service";
    public static final String UNIQUE_BINDING_NAME_MULTIPLY = "server.multiply_service";
    public static final String UNIQUE_BINDING_NAME_DIVISION ="server.division_service";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        // Создаём объекты сервисов
        final PlusService plusService = new PlusService();
        final MinusService minusService = new MinusService();
        final MultiplyService multiplyService = new MultiplyService();
        final DivisionService divisionService = new DivisionService();
        // Создаём реестр удалённых объектов, задаём ему номер порта
        final Registry registry = LocateRegistry.createRegistry(2732);
        // Создаём заглушки для каждого объекта сервисов, которые инкапсулируют весь процесс удалённого вызова.
        // Помимо объекта сервиса передаём в метод номер порта 0 - это значит, что он выберет случайный доступный
        // порт для служебного порта RMI (могут быть проблемы с браундмауэром/NATted, требующими открытия портов).
        Remote plusStub = UnicastRemoteObject.exportObject(plusService, 0);
        Remote minusStub = UnicastRemoteObject.exportObject(minusService, 0);
        Remote multiplyStub = UnicastRemoteObject.exportObject(multiplyService, 0);
        Remote divisionStub = UnicastRemoteObject.exportObject(divisionService, 0);
        // Регистрируем заглушки объектов сервисов в реестре удалённых объектов под их уникальными именами
        registry.bind(UNIQUE_BINDING_NAME_PLUS, plusStub);
        registry.bind(UNIQUE_BINDING_NAME_MINUS, minusStub);
        registry.bind(UNIQUE_BINDING_NAME_MULTIPLY, multiplyStub);
        registry.bind(UNIQUE_BINDING_NAME_DIVISION, divisionStub);

        // Ожидаем от пользователя завершения работы сервера - для этого надо ввести любой текст и нажать 'Enter'
        System.out.println("Server started. Enter any text to shut it down.");
        var scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine())
                break;
        }
        // Когда пользователь потребовал завершения работы, просто убираем с экспорта все объекты.
        // После этого реестр автоматически завершит работу.
        System.out.println("Server is shutting down. Bye!");
        UnicastRemoteObject.unexportObject(plusService, true);
        UnicastRemoteObject.unexportObject(minusService, true);
        UnicastRemoteObject.unexportObject(multiplyService, true);
        UnicastRemoteObject.unexportObject(divisionService, true);
    }
}
