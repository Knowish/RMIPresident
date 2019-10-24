package fr.univnantes.rmi.impl;
import fr.univnantes.impl.President;
import fr.univnantes.rmi.inter.PlayerInterface;
import fr.univnantes.rmi.inter.RmiService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class RmiServer extends Observable implements RmiService {

    private List<PlayerInterface> pendingPlayers;
    private int numberOfPendingPlayers;
    private final int NB_PLAYERS = 4; //the maximum of players in a game

    private class WrappedObserver implements Observer, Serializable {

        private static final long serialVersionUID = 1L;

        private PlayerInterface ro;

        public WrappedObserver(PlayerInterface ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
                ro.update(o.toString(), arg);
            } catch (RemoteException e) {
                System.out
                        .println("Remote exception removing observer:" + this);
                o.deleteObserver(this);
            }
        }

    }

    //TODO: On ferait pas un petit producteur consomateur ici?
    @Override
    synchronized public void joinGame(PlayerInterface o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        addObserver(mo);

        System.out.println("Added observer:" + mo);

        pendingPlayers.add(o);
        numberOfPendingPlayers ++ ;

        setChanged();
        notifyObservers(numberOfPendingPlayers);

        if (pendingPlayers.size() >= NB_PLAYERS) {
            List<PlayerInterface> startingPlayers = initGame();
            President game = new President(startingPlayers);
        }

    }

    //TODO : Voir si on peut pas mieux faire deux boucles c'est nul
    public List<PlayerInterface> initGame() throws RemoteException{
        System.out.println("On a tous les joueurs");
        List<PlayerInterface> startingPlayers = new ArrayList<>();

        //premiere boucle pour definir le nom des adversaires pour chaque joueur
        for (int i = 0; i < NB_PLAYERS; ++i) {
            PlayerInterface currentPlayer = pendingPlayers.get(i);

            currentPlayer.addOpponent(pendingPlayers.get((i+1) % 4));
            currentPlayer.addOpponent(pendingPlayers.get((i+2) % 4));
            currentPlayer.addOpponent(pendingPlayers.get((i+3) % 4));

            startingPlayers.add(currentPlayer);

        }

        //deuxieme boucle pour leur dire de commencer à jouer et les enlever de la liste des joueurs en attente
        for (int i = 0; i < NB_PLAYERS ; ++i) {
            PlayerInterface currentPlayer = pendingPlayers.remove(0);
            currentPlayer.startGame();
        }

        return startingPlayers;
    }

    @Override
    public int getNumberOfPendingPlayers() throws RemoteException {
        return numberOfPendingPlayers;
    }

    public RmiServer() {
        pendingPlayers= new ArrayList<>();
        numberOfPendingPlayers=0;
        thread.start();
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
            }
        }
    };

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(9999);
            RmiService rmiService = (RmiService) UnicastRemoteObject
                    .exportObject(new RmiServer(), 9999);
            rmiRegistry.bind("RmiService", rmiService);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
