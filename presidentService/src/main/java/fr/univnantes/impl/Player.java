package fr.univnantes.impl;

import fr.univnantes.gui.ConnectToServer;
import fr.univnantes.gui.GameBoard;
import fr.univnantes.gui.GuiBuilder;
import fr.univnantes.gui.Lobby;
import fr.univnantes.inter.PlayerInterface;
import fr.univnantes.inter.RmiService;

import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends UnicastRemoteObject implements PlayerInterface, Runnable {


    private String userName;
    private List<Card> hand;
    private boolean passTurn;
    private boolean myTurn;
    private Lobby lobby;
    private ConnectToServer connectToServerView;
    private List<PlayerInterface> opponents;
    private GameBoard gameBoard;
    private GuiBuilder guiBuilder;

    Player(String userName) throws RemoteException {
        this.userName = userName;
        hand = new ArrayList<>();
        passTurn = false;
        myTurn = false;
        opponents = new ArrayList<>();
    }

    public Player()  throws RemoteException{
        hand = new ArrayList<>();
        passTurn = false;
        myTurn = false;
        opponents = new ArrayList<>();
    }

    /**
     * allows a player to find a game of President
     * @return true if the connection is successful
     */
    public boolean findGame() {

        try {


            RmiService remoteService = (RmiService) Naming
                    .lookup("//localhost:9999/RmiService");

            remoteService.joinGame(this);
            System.out.println("Connected to server as " + this.userName);
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public Card playCard(Card lastCard, boolean mustPlaySameValue) {
        Card chosenCard = chooseCard(lastCard, mustPlaySameValue);
        myTurn = false;
        return chosenCard;
    }

    /**
     * Prompt the user to choose a card
     * @param lastCard the last card played
     * @param mustPlaySameValue true if two cards of same value have been played before
     * @return the card the player wishes to play
     */
    private Card chooseCard(Card lastCard, boolean mustPlaySameValue) {

        Card chosenCard = lastCard;
        List<Card> cardsICanPlay = this.cardsICanPlay(chosenCard, mustPlaySameValue);

        String cardName = gameBoard.promptCardChoice(cardsICanPlay, "Choose one of the following or pass your turn :");

        if(cardName==null || cardsICanPlay.isEmpty()) {
            if (mustPlaySameValue)
                setMyTurn(false);
            else
                pass();
        } else {
            chosenCard = findCardWithName(cardName);
            hand.remove(chosenCard);
            gameBoard.setTrick(chosenCard);
        }

        return chosenCard;
    }

    private Card findCardWithName(String cardName) {
        Card res = null;
        for (Card card : hand ){
            if (card.getName().equals(cardName)){
                res = card;
            }
        }
        return res;
    }

    @Override
    public void addToHand(Card card) {
        hand.add(card);
        sortHand();
    }

    void pass() {
        passTurn = true;
        setMyTurn(false);
    }

    @Override
    public boolean isPassTurn() {
        return passTurn;
    }

    @Override
    public void setPassTurn(boolean passTurn) {
        this.passTurn = passTurn;
        if(passTurn){
            setMyTurn(false);
        }
    }

    @Override
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void startGame() throws RemoteException {
        lobby.changeViewToBoardgame();
    }

    @Override
    public void update(Object observable, Object updateMsg) {
        System.out.println("Received a signal from the server");
        Integer waitingPlayers = (Integer) updateMsg;
        lobby.updateTextWaitingPlayer(waitingPlayers.toString());
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    @Override
    public void updateHandView() {
        gameBoard.updateCards(this.getHand());
    }

    @Override
    public void updateWhosPlaying() {
        updateHandView();
        gameBoard.displayWhosPlaying();
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public void addOpponent(PlayerInterface opponentName) {
        opponents.add(opponentName);
    }

    public List<PlayerInterface> getOpponents() {
        return opponents;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    private GameBoard getGameBoard(){return this.gameBoard;}

    public void updateTrick(Card card) {
        getGameBoard().setTrick(card);
    }

    /**
     * return the list of the cards the players have in its hand and that are playable ( >= to the last card played )
     * @param lastCardPlayed the last card placed on top of the trick
     * @param mustPlaySameValue true if two cards of same value have been played before
     * @return the list of the cards the player can play
     */
    private List<Card> cardsICanPlay(Card lastCardPlayed, boolean mustPlaySameValue) {
        List<Card> playableCards = new ArrayList<>();

        for (Card cardInMyHand : hand) {
            if (mustPlaySameValue) {
                if (cardInMyHand.compareTo(lastCardPlayed) == 0) {
                    playableCards.add(cardInMyHand);
                }
            } else {
                if (cardInMyHand.compareTo(lastCardPlayed) >= 0) {
                    playableCards.add(cardInMyHand);
                }
            }
        }
        return playableCards;
    }

    @Override
    public void sortHand() {
        Collections.sort(this.hand);
    }

    /**
     * échange une carte avec un autre joueur
     * @param winOrder la place à laquelle le joueur a terminé la partie 1 if the player is the president, 4 if he is the trou du cul
     */
    @Override
    public Card exchangeCard(int winOrder) {
        Card exchangedCard = null;

        switch (winOrder){
            //si le joueur est le president, il choisit la carte qu'il souhaite donner au trou du cul
            case 1:

                exchangedCard = chooseOneCardToExchange();

                gameBoard.showUserExchangedCards(exchangedCard, 4);
                break;

                // si le joueur est le vicePresident il peut choisir la carte qu'il donne au vice trou
            case 2:

                exchangedCard = chooseOneCardToExchange();

                gameBoard.showUserExchangedCards(exchangedCard, 3);
                break;

                //si le joueur est le vice trou, il doit donner sa meilleur carte au vice pres
            case 3:

                exchangedCard = getBestCard();
                gameBoard.showUserExchangedCards(exchangedCard, 2);
                break;

                //si le joueur est le trou il doit donner sa meilleur carte au pres
            case 4 :
                exchangedCard = getBestCard();
                gameBoard.showUserExchangedCards(exchangedCard, 1);
                break;

            default :
                break;
        }
        return exchangedCard;

    }

    private Card chooseOneCardToExchange() {
        String cardName = gameBoard.promptCardChoice(hand, "Choose one of your cards to exchange");

        while (cardName==null){
            cardName = gameBoard.promptCardChoice(hand, "Choose one of your cards to exchange");
        }

        return findCardWithName(cardName);
    }

    private Card getBestCard(){
        return  hand.get(hand.size()-1);
    }

    @Override
    public void removeCardFromHand(Card cardToRemove) {
        hand.remove(cardToRemove);
    }

    @Override
    public boolean askKeepPlaying(int rank) {
        return gameBoard.askKeepPlaying(rank);
    }

    @Override
    public void goBackToLogin(String message) throws RemoteException {
        if (!message.equals("")){
            connectToServerView.displayMessage(message);
        }
        this.guiBuilder.dispose();
        Player myPlayer = new Player();
        myPlayer.run();
    }

    private void displayGui(){
        GuiBuilder frame = new GuiBuilder(this);
        this.guiBuilder = frame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void run() {
        displayGui();
    }

    public void setConnectToServerView(ConnectToServer connectToServer) {
        this.connectToServerView = connectToServer;
    }
}
