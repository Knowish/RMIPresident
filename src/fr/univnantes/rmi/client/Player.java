package fr.univnantes.rmi.client;

import fr.univnantes.rmi.Card;

import java.util.List;

public class Player extends Client {
    private List<Card> hand;
    private boolean passTurn = false;

    public void playCard(Card card) {

    }

    public void pass() {
        passTurn = true;
    }
}
