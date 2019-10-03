package fr.univnantes.rmi.impl;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player extends UnicastRemoteObject implements PropertyChangeListener {

    private UUID identifier;
    private String userName;
    private List<Card> hand;
    private boolean passTurn = false;
    private int numberOfPendingPlayers;
    private PropertyChangeSupport support;

    public Player(UUID identifier, String userName) throws RemoteException {
        hand = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public void playCard(Card card) {
        if (!passTurn) {
            hand.remove(card);
        }
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void pass() {
        passTurn = true;
    }

    public void getHand() {
        for (Card c : hand) {
            System.out.println(c.getName());
        }
    }

    public String getUserName() {
        return userName;
    }

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

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
