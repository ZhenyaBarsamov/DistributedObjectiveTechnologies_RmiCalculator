import java.rmi.RemoteException;

// Сервис сложения двух действительных чисел
public class PlusService implements OperationService {
    @Override
    public double applyOperation(double op1, double op2) throws RemoteException {
        return op1 + op2;
    }
}
