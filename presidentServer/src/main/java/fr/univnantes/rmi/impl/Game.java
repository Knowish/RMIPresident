package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.impl.Card;
import fr.univnantes.rmi.impl.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Queue<Player> players;
    private List<Card> board;

    public Game(Queue<Player> players) {
        this.players = new ArrayDeque<>();
        this.players.addAll(players);
        this.board = new ArrayList<>();
    }

    public void init() {

    }
}
