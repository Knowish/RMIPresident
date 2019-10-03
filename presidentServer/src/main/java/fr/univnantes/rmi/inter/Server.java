package fr.univnantes.rmi.inter;

import fr.univnantes.rmi.impl.Game;
import fr.univnantes.rmi.impl.Player;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends  Remote {
    void apply(Player client) throws RemoteException;

    Game getGame() throws RemoteException;

    String getPendingPlayers() throws RemoteException;

    void addPropertyChangeListener(PropertyChangeListener pc) throws RemoteException;

    void removePropertyChangeListener(PropertyChangeListener pc) throws RemoteException;
}
