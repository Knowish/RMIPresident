import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServeurCardGame extends UnicastRemoteObject implements Cardgame {

    protected ServeurCardGame() throws RemoteException {
    }

    @Override
    public String affiche() throws RemoteException {
        return "Hello World";
    }
}
