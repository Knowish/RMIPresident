package fr.univnantes.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
    protected ServerImpl() throws RemoteException {
    }
}
