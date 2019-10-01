package fr.univnantes.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerCardGame extends UnicastRemoteObject implements CardGame {

    public ServerCardGame() throws RemoteException {
    }

    @Override
    public String affiche() throws RemoteException {
        return "Hello World";
    }
}
