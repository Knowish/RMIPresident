package fr.univnantes.sync;

import fr.univnantes.inter.PlayerInterface;
import fr.univnantes.sync.KeepPlaying;

import java.rmi.RemoteException;

public class PlayerKeepPlayingRunnable implements Runnable {

    private PlayerInterface player;
    private KeepPlaying lock;
    private int rank; //the rank of the player


    PlayerKeepPlayingRunnable(KeepPlaying keepPlaying, PlayerInterface player, int rank) {
        this.lock = keepPlaying;
        this.player = player;
        this.rank = rank;
    }

    @Override
    public void run() {

        try {
            boolean answer = player.askKeepPlaying(rank);
            lock.answer(answer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
