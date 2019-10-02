package fr.univnantes.rmi.client;

import fr.univnantes.rmi.Card;
import fr.univnantes.rmi.server.Server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Client {
    private static final long serialVersionUID = -1486356081005657631L;


    private String userName;
    private List<Card> hand;
    private boolean passTurn = false;

    public Player() {super();}

    public Player(String name, Server server) {
        super(name, server);
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
