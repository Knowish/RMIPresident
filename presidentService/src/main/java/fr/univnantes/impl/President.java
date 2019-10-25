package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static fr.univnantes.impl.ConstUtils.HEART;
import static fr.univnantes.impl.ConstUtils.QUEEN;

public class President extends Game implements Runnable {

    private List<PlayerInterface> winOrder = new ArrayList<>();
    private boolean firstRound = true;

    public President() {
        super();
    }

    public President(List<PlayerInterface> players) {
        super(players);
        new Thread(this).start();
    }

    @Override
    public void playGame() throws RemoteException, InterruptedException {
        while(!gameOver){
            initializeGame();
            int i = this.players.indexOf(identifyFirstPlayer());
            System.out.println(this.board.toString());
            System.out.println("Le board doit être vide pour commencer : " + this.board.isEmpty());

            this.winOrder.clear();
            while(!roundIsDone()) {
                PlayerInterface currentPlayer = this.players.get(i);
                currentPlayer.setMyTurn(true);
                for(PlayerInterface player : players) {
                    player.updateWhosPlaying();
                }
                playerPlay(currentPlayer);
                i = (i+1) % this.players.size();
            }
            firstRound = false;
        }
    }

    //Defini la phase d'échange de cartes entre les joueurs
    private void exchangeCardsPhase(List<PlayerInterface> winOrder) throws RemoteException, InterruptedException {

        //le president doit donner ses deux cartes les moins fortes au trou du cul
        //le trou du cul doit donner ses deux cartes les moins fortes au president;
        Exchanger<Card> exchangerPresidentTrouduc = new Exchanger<>();
        Exchanger<Card> exchangerVices = new Exchanger<>();

        List<ExchangerRunnable> listOfThreads = new ArrayList<>();

        ExchangerRunnable exchangerRunnablePresident =
                new ExchangerRunnable(exchangerPresidentTrouduc, winOrder.get(0), 1);
        listOfThreads.add(exchangerRunnablePresident);

        ExchangerRunnable exchangerRunnableTrouduc =
                new ExchangerRunnable(exchangerPresidentTrouduc, winOrder.get(winOrder.size()-1), 4 );
        listOfThreads.add(exchangerRunnableTrouduc);

        ExchangerRunnable exchangerRunnableVicePres =
                new ExchangerRunnable(exchangerVices, winOrder.get(1), 2 );
        listOfThreads.add(exchangerRunnableVicePres);

        ExchangerRunnable exchangerRunnableViceTrou =
                new ExchangerRunnable(exchangerVices, winOrder.get(winOrder.size()-2), 3 );
        listOfThreads.add(exchangerRunnableViceTrou);

        ExecutorService es = Executors.newFixedThreadPool(4);

        for (int i=0; i<4; ++i){
            es.execute(listOfThreads.get(i));
        }
        System.out.println("J'ai fini de lancer tout les threads");
        es.shutdown();
        System.out.println("J'ai shutdown les threads");
        es.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("AwaitTermination a fini");

        /*
        new Thread(exchangerRunnablePresident).start();
        new Thread(exchangerRunnableTrouduc).start();
        new Thread(exchangerRunnableVicePres).start();
        new Thread(exchangerRunnableViceTrou).start();*/

    }

    @Override
    public boolean roundIsDone() throws RemoteException {
        boolean result = true;
        if (this.winOrder.size() == this.players.size()-1) {//if there is only one player left with cards, game ends
                                                            // and this player is the loser
            for (PlayerInterface p : this.players) {
                if(!p.getHand().isEmpty()) {
                    this.winOrder.add(p);
                }
            }
        } else {
            for (PlayerInterface p : this.players) {
                if (!p.getHand().isEmpty())
                    result = false;
            }
        }
        return result;
    }

    public PlayerInterface identifyFirstPlayer() throws RemoteException {
        PlayerInterface hasTheHand = this.players.get(0);

        if (firstRound) {//for the first round, the first player is the one who got the Queen of Hearts
            Card queenOfHearts = new Card(12, QUEEN + " " + HEART);

            for (PlayerInterface p : this.players) {
                if (p.getHand().contains(queenOfHearts))
                    hasTheHand = p;
            }
        } else {  //the rest of the time, the loser starts
            hasTheHand = winOrder.get(winOrder.size()-1);
        }
        return hasTheHand;
    }

    public void playerPlay(PlayerInterface currentPlayer) throws RemoteException, InterruptedException {

        Card lastCardOnBoard;

        //si tout le monde a passé son tours
        if (noPlayersRemaining(currentPlayer)) { //Just to fix a tricky case
            cleanGame();
        }

        //si le joueur n'a pas passé son tour auparavent et s'il a des cartes en main
        if (!currentPlayer.isPassTurn()
                && !currentPlayer.getHand().isEmpty()) {

            //si aucune carte n'a encore été placé au milieu au place une carte de valeur 0
            if (this.board.isEmpty()) {
                lastCardOnBoard = new Card(0, "");
            } else {
                //sinon on actualise la valeur de la dernière carte à avoir été jouée
                lastCardOnBoard = this.board.get(this.board.size() - 1);
            }
            //le joueur joue une carte
            Card playedCard = currentPlayer.playCard(lastCardOnBoard);

            //if the player played a card
            if (!playedCard.equals(lastCardOnBoard)) {
                this.board.add(playedCard); //Last Card is added at the end of the list
                updateAllTricks(playedCard);

                //si le joueur à posé toute ses cartes, il a fini pour ce round
                if (currentPlayer.getHand().isEmpty()) {
                    this.winOrder.add(currentPlayer);
                    cleanGame();
                    System.out.println("Le joueur " + currentPlayer.getUserName()
                            + " a posé toutes ses cartes ! Il est le n°" + (winOrder.indexOf(currentPlayer)+1)
                            + " à terminer.");
                }
                //The player plays a 2, or formed a square, or everyone passed his turn
                else if (!currentPlayer.getHand().isEmpty() &&
                        (playedCard.getValue() == 15
                                || squareFormed()
                                || noPlayersRemaining(currentPlayer))) {
                    System.out.println("Le joueur " + currentPlayer.getUserName() + " prend la main.");
                    cleanGame();
                    playerPlay(currentPlayer);//Play again if you have taken over
                }
            }
        }

        currentPlayer.setMyTurn(false);

    }

    private void updateAllTricks(Card playedCard) throws RemoteException {
        for(PlayerInterface p : players){
            p.updateTrick(playedCard);
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
            if (p != lastPlayer) {
                survivor = survivor && (p.isPassTurn() || p.getHand().isEmpty());
            }
        }
        return survivor;
    }

    public void cleanGame() throws RemoteException {
        this.board.clear();
        Card CardOnBoard = new Card(0, "");
        this.board.add(CardOnBoard);
        for (PlayerInterface p : this.players) {
            p.setPassTurn(false);
            p.updateTrick(CardOnBoard);
        }
    }

    public final void initializeGame() throws RemoteException, InterruptedException {
        gameOver = false;
        generateCardPool();
        distribution();
        if(!firstRound){
            //at the begining of the second round and all the other ones, the president exchange two cards with the trou du cul
            //and the vice-president exchange one card with the vice-trou du cul
            exchangeCardsPhase(winOrder);
        }
    }

    @Override
    public void run() {
        try {
            playGame();
        } catch (RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
