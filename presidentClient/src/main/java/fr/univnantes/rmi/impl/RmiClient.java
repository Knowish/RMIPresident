package fr.univnantes.rmi.impl;
import fr.univnantes.impl.Player;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends UnicastRemoteObject {

    private RmiClient() throws RemoteException {
        super();
    }

    public static void main(String[] args) {

        try {

            new RmiClient();
            Player myPlayer = new Player();
            myPlayer.run();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
