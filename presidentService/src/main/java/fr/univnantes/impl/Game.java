package fr.univnantes.impl;

import fr.univnantes.impl.Card;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    private Queue<Player> players;
    private List<Card> board;

    public Game(List<Player> players) {
        this.players = new ArrayDeque<>(4);
        this.players.addAll(players);
        this.board = new ArrayList<>();
        distribution();
    }

    /*public void init() {
        List<Card> deck = new CardPool().getDeck();
        ArrayList<Player> players1 = new ArrayList<>(players);
        int cardsByPlayer = deck.size()/players.size();
        for (int i = 0; i < cardsByPlayer; ++i) {
            for (int j = 0; j < players.size(); ++j) {
                int index = (int) (Math.random()*deck.size());
                players1.get(j).addToHand(deck.remove(index));
            }
        }
        int k = 1;
        for(Player p : players1) {
            System.out.println("\nPlayer " + (k++));
            p.getHand();
        }
    }*/

    private void distribution() {
        List<Card> deck = new CardPool().getDeck();
        Collections.shuffle(deck);
        ArrayList<Player> uselessListOfPlayers = new ArrayList<>(players);

        for (int i = 0; i < deck.size(); ++i) {
            uselessListOfPlayers.get(i % players.size()).addToHand(deck.get(i));
        }

        for(Player p : uselessListOfPlayers) {
            System.out.println("\nPlayer " + p.getUserName());
            p.getHand();
        }
    }
}
