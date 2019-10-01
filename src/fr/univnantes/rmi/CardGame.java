package fr.univnantes.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CardGame extends Remote {
    public String affiche() throws RemoteException;
}
