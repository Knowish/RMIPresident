package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;

import java.rmi.RemoteException;
import java.util.List;

import static fr.univnantes.impl.ConstUtils.HEART;
import static fr.univnantes.impl.ConstUtils.QUEEN;

public class President extends Game {

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

        while(!isDone()) {
            this.players.get(i).play(); //TODO: some method play() into PlayerInterface ?
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

}
