package fr.univnantes.rmi.inter;

import fr.univnantes.impl.Card;
import fr.univnantes.impl.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface PlayerInterface extends Remote {

    String getUserName() throws RemoteException;

    void setUserName(String username) throws RemoteException;

    void addToHand(Card card) throws RemoteException;

    void getHand() throws RemoteException;

    void startGame() throws RemoteException;

    void update (Object observable, Object updateMsg) throws RemoteException;
}
