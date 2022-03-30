import java.rmi.Remote;
import java.rmi.RemoteException;

// Интерфейс удалённых сервисов операций.
// Каждый сервис (сервис сложения, ...) должен реализовать данный интерфейс
public interface OperationService extends Remote {
    // Применить операцию к числовым (double) операндам op1 и op2 и вернуть результат
    double applyOperation(double op1, double op2) throws RemoteException;
}
