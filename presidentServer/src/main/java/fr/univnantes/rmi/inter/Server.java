package fr.univnantes.rmi.inter;

import fr.univnantes.rmi.impl.Card;
import fr.univnantes.rmi.impl.Game;
import fr.univnantes.rmi.impl.Player;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Server extends  Remote {
    void apply(UUID identifier, String username) throws RemoteException;

    Game getGame() throws RemoteException;

    String getPendingPlayers() throws RemoteException;

    void playCard(UUID identifier, Card card) throws RemoteException ;

    int getNumberOfPendingPlayers() throws RemoteException ;
}
