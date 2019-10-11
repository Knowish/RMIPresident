package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import static fr.univnantes.impl.ConstUtils.*;

abstract class Game implements Serializable {

    protected List<PlayerInterface> players = new ArrayList<>();
    protected List<Card> board = new ArrayList<>();

    abstract void playGame() throws RemoteException, InterruptedException;
    abstract boolean isDone() throws RemoteException;
    abstract PlayerInterface identifyFirstPlayer() throws RemoteException;

    public Game(){}

    public Game(List<PlayerInterface> players) {
        this.players.addAll(players);
    }

    public final void addPlayer(PlayerInterface player) {
        this.players.add(player);
    }

    public final void initializeGame() throws RemoteException {
        generateCardPool();
        distribution();
    }

    public void distribution() throws RemoteException {
        for (int i = 0; i < this.board.size(); ++i) {
            this.players.get(i % this.players.size()).addToHand(this.board.get(i));
        }
        this.board.clear();
    }

    public void generateCardPool() {

        String[] colors = {SPADE, HEART, DIAMOND, CLUB};

        for (String color : colors) {
            for  (int i = MIN_VALUE_CARD; i < NUMBER_CARDS_PER_COLOR + MIN_VALUE_CARD; ++i) { // i : 3 -> 15
                this.board.add(new Card(i, matchValueToName(i) + " " + color));
            }
        }
        Collections.shuffle(this.board);
    }

    private String matchValueToName(int value) {
        String name;
        switch (value) {
            case 11:
                name = JACK;
                break;
            case 12:
                name = QUEEN;
                break;
            case 13:
                name = KING;
                break;
            case 14:
                name = ACE;
                break;
            case 15:
                name = "2";
                break;
            default:
                name = String.valueOf(value);
                break;
        }
        return name;
    }

}
