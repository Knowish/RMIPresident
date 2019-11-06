package fr.univnantes.impl;

import fr.univnantes.inter.PlayerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import static fr.univnantes.constantes.CardConst.*;

/**
 * represente une partie
 */
abstract class Game implements Serializable {

    List<PlayerInterface> players = new ArrayList<>();
    List<Card> board = new ArrayList<>();
    boolean gameOver;

    abstract void playGame() throws RemoteException, InterruptedException;
    abstract boolean roundIsDone() throws RemoteException;
    abstract PlayerInterface identifyFirstPlayer() throws RemoteException;

    Game(){}

    Game(List<PlayerInterface> players) {
        this.players.addAll(players);
    }

    final void addPlayer(PlayerInterface player) {
        this.players.add(player);
    }

    void distribution() throws RemoteException {
        for (int i = 0; i < this.board.size(); ++i) {
            this.players.get(i % this.players.size()).addToHand(this.board.get(i));
        }
        this.board.clear();
        for (PlayerInterface p : this.players) {
            p.sortHand();
        }
    }

    void generateCardPool() {

        String[] colors = {SPADE, HEART, DIAMOND, CLUB};
        this.board.clear();
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


    List<PlayerInterface> getPlayers() {
        return players;
    }

    List<Card> getBoard() {
        return board;
    }

}
