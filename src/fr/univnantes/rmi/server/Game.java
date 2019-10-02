package fr.univnantes.rmi.server;

import fr.univnantes.rmi.Card;
import fr.univnantes.rmi.CardPool;
import fr.univnantes.rmi.client.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Queue<Player> players;
    private List<Card> board;

    public Game(List<Player> players) {
        this.players = new ArrayDeque<>(4);
        this.players.addAll(players);
        this.board = new ArrayList<>();
        init();
    }

    public void init() {
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
    }
}
