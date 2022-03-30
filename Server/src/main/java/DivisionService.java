import java.rmi.RemoteException;

// Сервис деления двух действительных чисел
public class DivisionService implements OperationService {
    @Override
    public double applyOperation(double op1, double op2) throws RemoteException {
        return op1 / op2;
    }
}
