package fr.univnantes.rmi.impl;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String userName;
    private List<Card> hand;
    private boolean passTurn = false;

    public Player(String name) {
        userName = name;
        hand = new ArrayList<>();
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

}
