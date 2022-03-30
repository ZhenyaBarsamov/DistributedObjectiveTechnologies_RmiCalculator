import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller();
            controller.readExpressionAndCalculate();
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Не удалось подключиться к серверу");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
