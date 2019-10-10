package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static fr.univnantes.impl.ConstUtils.HEART;
import static fr.univnantes.impl.ConstUtils.QUEEN;

public class President extends Game {

    private List<PlayerInterface> winOrder = new ArrayList<>();

    public President() {
        super();
    }

    public President(List<PlayerInterface> players) {
        super(players);
    }

    @Override
    public void playGame() throws RemoteException {
        initializeGame();
        int i = this.players.indexOf(identifyFirstPlayer());
        System.out.println("Le board doit être vide pour commencer : " + this.board.isEmpty());
        while(!isDone()) {
            playerPlay(i);
            i = (i+1) % this.players.size();
        }
    }

    @Override
    public boolean isDone() throws RemoteException {
        boolean result = true;
        for (PlayerInterface p : this.players) {
            if (!p.getHand().isEmpty())
                result = false;
        }
        return result;
    }

    public PlayerInterface identifyFirstPlayer() throws RemoteException {
        Card queenOfHearts = new Card(12, QUEEN + " " + HEART);
        PlayerInterface hasTheHand = this.players.get(0);

        for (PlayerInterface p : this.players) {
            if (p.getHand().contains(queenOfHearts))
                hasTheHand = p;
        }
        return hasTheHand;
    }

    public void playerPlay(int playerNumber) throws RemoteException {
        PlayerInterface currentPlayer = this.players.get(playerNumber);
        Card lastCardOnBoard;

        if (allPlayersPassTurn()) //Just to fix a tricky case
            cleanGame();

        if (!currentPlayer.isPassTurn()
                && !currentPlayer.getHand().isEmpty()) {

            if (!this.board.isEmpty()) {
                lastCardOnBoard = this.board.get(this.board.size() - 1);
            } else {
                lastCardOnBoard = new Card(0, "");
            }
            Card playedCard = currentPlayer.playCard(lastCardOnBoard);

            if (!playedCard.equals(lastCardOnBoard)) { //The player played a card
                this.board.add(playedCard); //Last Card is added at the end of the list

                if (currentPlayer.getHand().isEmpty()) {
                    this.winOrder.add(currentPlayer);
                    cleanGame();
                    System.out.println("Le joueur " + currentPlayer.getUserName()
                            + " a posé toutes ses cartes ! Il est le n°" + winOrder.indexOf(currentPlayer)
                            + " à terminer.");
                }

                if (!currentPlayer.getHand().isEmpty() &&
                        (playedCard.getValue() == 15
                                || squareFormed()
                                || noPlayersRemaining(currentPlayer))) {
                    //The player plays a 2, or formed a square, or everyone passed his turn
                    cleanGame();
                    playerPlay(playerNumber);//Play again if you have taken over
                }
            }
        }

    }

    public boolean squareFormed() {
        int nbCard = this.board.size();
        return nbCard >= 4
                && this.board.get(nbCard -1).getValue() == this.board.get(nbCard -2).getValue()
                && this.board.get(nbCard -2).getValue() == this.board.get(nbCard -3).getValue()
                && this.board.get(nbCard -3).getValue() == this.board.get(nbCard -4).getValue();

    }

    public boolean noPlayersRemaining(PlayerInterface lastPlayer) throws RemoteException {
        boolean survivor = true;
        for (PlayerInterface p : this.players) {
            if (p != lastPlayer)
                survivor = survivor && (p.isPassTurn() || p.getHand().isEmpty());
        }
        return survivor;
    }

    public void cleanGame() throws RemoteException {
        this.board.clear();
        for (PlayerInterface p : this.players) {
            p.setPassTurn(false);
        }
    }

    public boolean allPlayersPassTurn() throws RemoteException {
        boolean gameBlocked = true;
        for (PlayerInterface p : this.players) {
            gameBlocked = gameBlocked && p.isPassTurn();
        }
        return gameBlocked;
    }

}