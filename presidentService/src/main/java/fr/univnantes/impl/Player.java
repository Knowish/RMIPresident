package fr.univnantes.impl;
import fr.univnantes.gui.Lobby;
import fr.univnantes.impl.Card;
import fr.univnantes.rmi.inter.PlayerInterface;
import fr.univnantes.rmi.inter.RmiService;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player extends UnicastRemoteObject implements PropertyChangeListener, PlayerInterface {


    private String userName;
    private List<Card> hand;
    private boolean passTurn;
    private int numberOfPendingPlayers;
    private PropertyChangeSupport support;
    private boolean myTurn = false;
    private Lobby lobby;
    private RmiService remoteService;

    public Player(String userName) throws RemoteException {
        this.userName = userName;
        hand = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        passTurn = false;
    }

    public Player()  throws RemoteException{
        hand = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        passTurn = false;
    }

    public boolean findGame() {

        try {

            remoteService = (RmiService) Naming
                    .lookup("//localhost:9999/RmiService");

            remoteService.joinGame(this);
            JOptionPane.showMessageDialog(null, "Connected to server as " + this.userName);
            System.out.println("Connected to server as " + this.userName);
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public Card playCard(Card lastCard) throws RemoteException {
        Card chosenCard = lastCard;
        if (!passTurn) {
            while(chosenCard.compareTo(lastCard) < 0 && !passTurn) {
                chosenCard = chooseCard();
            }
            hand.remove(chosenCard);
        }
        return chosenCard;
    }

    public Card chooseCard() {
        //TODO
        return null;
    }

    @Override
    public void addToHand(Card card) throws RemoteException {
        hand.add(card);
    }

    public void pass() {
        passTurn = true;
    }

    @Override
    public boolean isPassTurn() throws RemoteException {
        return passTurn;
    }

    @Override
    public void setPassTurn(boolean passTurn) throws RemoteException {
        this.passTurn = passTurn;
    }

    @Override
    public void setMyTurn(boolean myTurn) throws RemoteException {
        this.myTurn = myTurn;
    }

    @Override
    public List<Card> getHand() throws RemoteException {
        return hand;
    }

    @Override
    public String getUserName() throws RemoteException {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setNumberOfPendingPlayers((int) evt.getNewValue());
    }

    public int getNumberOfPendingPlayers() {
        return numberOfPendingPlayers;
    }

    public void setNumberOfPendingPlayers(int numberOfPendingPlayers) {
        support.firePropertyChange("numberOfPendingPlayers", this.numberOfPendingPlayers, numberOfPendingPlayers);
        this.numberOfPendingPlayers = numberOfPendingPlayers;
    }

    @Override
    public void startGame() throws RemoteException {
        System.out.println("On devrait commencer la partie mdr");
        lobby.changeViewToBoardgame();
    }

    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException {
        System.out.println("Received a signal from the server");
        Integer waitingPlayers = (Integer) updateMsg;
        lobby.updateTextWaitingPlayer(waitingPlayers.toString());
        setNumberOfPendingPlayers(waitingPlayers);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public int getWaitingPlayers() throws RemoteException {
        return remoteService.getNumberOfPendingPlayers();
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

}
