package fr.univnantes.rmi.client;

import fr.univnantes.rmi.Card;

import java.util.List;

public class Player extends Client {


    private String userName;
    private List<Card> hand;
    private boolean passTurn = false;

    public void playCard(Card card) {

    }

    public void pass() {
        passTurn = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
