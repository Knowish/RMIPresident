package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
    public ServerImpl() throws RemoteException {
    }
}
