import java.rmi.RemoteException;

// Сервис умножения двух действительных чисел
public class MultiplyService implements OperationService {
    @Override
    public double applyOperation(double op1, double op2) throws RemoteException {
        return op1 * op2;
    }
}
