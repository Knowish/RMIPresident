import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cardgame extends Remote {
    public String affiche() throws RemoteException;
}
