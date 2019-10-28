package fr.univnantes.impl;

import fr.univnantes.inter.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeepPlaying {

    private int nbPlayers;

    private boolean continuePlaying = true;
    private boolean allAnswers = false;

    private int nbAnswers=0;
    private List<PlayerInterface> players;

    public KeepPlaying(List<PlayerInterface> players) {
        this.players = players;
        nbPlayers = players.size();
    }

    /**
     * return true if all players agree to keep playing or false if a timeout is reached or a player decided to stop playing
     * @return
     * @throws InterruptedException
     */
    public synchronized boolean continuePlaying() {
        boolean keepPlaying = true;

        ExecutorService es = Executors.newFixedThreadPool(nbPlayers);

        for (PlayerInterface player : players){
            es.execute(new PlayerKeepPlayingRunnable(this, player, players.indexOf(player)+1));
        }
        es.shutdown();

        while(continuePlaying && !allAnswers){
            try {
                long timeBefore = System.currentTimeMillis();
                wait(30000);
                if(System.currentTimeMillis() - timeBefore > 30000 ){
                    keepPlaying = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!continuePlaying){
            keepPlaying = false;
        }

        return keepPlaying;
    }

    public synchronized void answer(boolean answer){
        if(!answer){
            continuePlaying = false;
        }
        nbAnswers++;
        if(nbAnswers==nbPlayers){
            allAnswers = true;
        }
        notify();
    }

}
