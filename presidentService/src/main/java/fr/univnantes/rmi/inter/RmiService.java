package fr.univnantes.rmi.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends  Remote {

    void   joinGame(PlayerInterface o) throws RemoteException;
    int getNumberOfPendingPlayers() throws RemoteException;
}
