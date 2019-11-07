package fr.univnantes.rmi.impl;
import fr.univnantes.impl.President;
import fr.univnantes.inter.PlayerInterface;
import fr.univnantes.inter.RmiService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class RmiServer extends Observable implements RmiService {

    private List<PlayerInterface> pendingPlayers;
    private List<ClientObserver> observers;
    private int numberOfPendingPlayers;
    private final int NB_PLAYERS = 4; //the maximum of players in a game

    /**
     * allows a player to join a game
     * @param obs the new player who joins the game
     * @throws RemoteException when the server cannot communicate with the player
     */
    @Override
    synchronized public void joinGame(PlayerInterface obs) throws RemoteException {

        //add the player as an observer so he can be notified when another player
        //joins the game
        ClientObserver mo = new ClientObserver(obs);
        addObserver(mo);

        for (PlayerInterface p : pendingPlayers) {
            try {
                p.getUserName();
            } catch (RemoteException e) {
                pendingPlayers.remove(p);
                --numberOfPendingPlayers;
            }
        }

        pendingPlayers.add(obs);
        observers.add(mo);
        numberOfPendingPlayers++;

        setChanged();
        notifyObservers(numberOfPendingPlayers);

        if (pendingPlayers.size() == NB_PLAYERS) {

            List<PlayerInterface> startingPlayers = initGame();
            new President(startingPlayers);
            numberOfPendingPlayers = pendingPlayers.size() ;
        }

    }

    /**
     * Initialise the game by sending to all the players their opponents, removing all the players
     * from the pending players and change their view
     * @return the players who are in the same game
     * @throws RemoteException when the player cannot communicate with the server
     */
    private List<PlayerInterface> initGame() throws RemoteException {

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
            deleteObserver(observers.remove(0));
            currentPlayer.startGame();
        }
        return startingPlayers;
    }

    private RmiServer() {
        pendingPlayers= new ArrayList<>();
        observers = new ArrayList<>();
        numberOfPendingPlayers=0;
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });
        thread.start();
    }

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
