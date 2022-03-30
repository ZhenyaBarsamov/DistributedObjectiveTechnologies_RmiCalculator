import java.rmi.RemoteException;

// Сервис вычитания двух действительных чисел
public class MinusService implements OperationService {
    @Override
    public double applyOperation(double op1, double op2) throws RemoteException {
        return op1 - op2;
    }
}
