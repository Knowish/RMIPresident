package fr.univnantes.inter;

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

    void addOpponent(PlayerInterface opponent) throws RemoteException;

    Card playCard(Card lastCard, boolean mustPlaySameValue) throws RemoteException;

    boolean isPassTurn() throws RemoteException;

    void setPassTurn(boolean passTurn) throws RemoteException;

    void setMyTurn(boolean myTurn) throws RemoteException;

    boolean isMyTurn() throws RemoteException;

    void updateHandView() throws RemoteException;

    void updateWhosPlaying() throws RemoteException;

    void updateTrick(Card card) throws RemoteException;

    void sortHand() throws RemoteException;

    Card exchangeCard(int i) throws RemoteException;

    void removeCardFromHand(Card cardToRemove) throws RemoteException;

    boolean askKeepPlaying(int rank) throws RemoteException;

    void goBackToLogin(String message) throws RemoteException;
}
