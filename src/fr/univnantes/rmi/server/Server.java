package fr.univnantes.rmi.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Remote {
    private Game game;

    protected Server() throws RemoteException {
    }
}
