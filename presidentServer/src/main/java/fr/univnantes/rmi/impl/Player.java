package fr.univnantes.rmi.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements PropertyChangeListener, Serializable {

    private String userName;
    private List<Card> hand;
    private boolean passTurn = false;
    private int numberOfPendingPlayers;

    public Player(){
        hand = new ArrayList<>();
        numberOfPendingPlayers = 1;
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
        this.numberOfPendingPlayers = numberOfPendingPlayers;
    }
}
