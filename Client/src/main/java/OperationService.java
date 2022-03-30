import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OperationService extends Remote {
    double applyOperation(double op1, double op2) throws RemoteException;
}
