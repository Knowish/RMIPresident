package fr.univnantes.rmi.inter;

import fr.univnantes.impl.Card;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PlayerInterface extends Remote {

    String getUserName() throws RemoteException;

    void setUserName(String username) throws RemoteException;

    void addToHand(Card card) throws RemoteException;

    List<Card> getHand() throws RemoteException;

    void startGame() throws RemoteException;

    void update (Object observable, Object updateMsg) throws RemoteException;

    void setOrderOfPlay(int orderOfPlay) throws RemoteException;

    void addOpponentsName(String opponentName) throws RemoteException;


    Card playCard(Card lastCard) throws RemoteException;

    boolean isPassTurn() throws RemoteException;

    void setPassTurn(boolean passTurn) throws RemoteException;

    void setMyTurn(boolean myTurn) throws RemoteException;
}
