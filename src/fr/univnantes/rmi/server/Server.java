package fr.univnantes.rmi.server;

import fr.univnantes.rmi.client.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    void apply(Client client) throws RemoteException;
    Game getGame() throws RemoteException;
    String getPendingPlayers() throws RemoteException;
}
