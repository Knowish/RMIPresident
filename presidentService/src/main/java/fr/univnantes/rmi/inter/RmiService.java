package fr.univnantes.rmi.inter;

import fr.univnantes.impl.Card;
import fr.univnantes.impl.Game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface RmiService extends  Remote {

    void addObserver(RemoteObserver o, UUID identifier, String username) throws RemoteException;
    int getNumberOfPendingPlayers() throws RemoteException;
}
