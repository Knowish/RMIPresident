package fr.univnantes.rmi.server;

import fr.univnantes.rmi.client.Client;
import fr.univnantes.rmi.client.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ServerImpl extends UnicastRemoteObject implements Server {

    private Game game;
    private Queue<Player> pendingPlayers = new ArrayDeque<>();

    protected ServerImpl() throws RemoteException {
    }

    @Override
    public void apply(Client client) throws RemoteException {

    }

    @Override
    public void apply(Player client) throws RemoteException {
        pendingPlayers.add(client);
        if (pendingPlayers.size() >= 4) {
            List<Player> startingPlayers = new ArrayList<>();
            for (int i = 0; i < 4; ++i) {
                startingPlayers.add((Player) pendingPlayers.remove());
            }
            game = new Game(startingPlayers);
        }
    }

    @Override
    public Game getGame() throws RemoteException {
        return game;
    }

    @Override
    public String getPendingPlayers() throws RemoteException {
        /*StringBuilder res = new StringBuilder();
        for (Player c : pendingPlayers) {
            res.append(c.getName()).append(" is waiting for a game\n");
        }
        return res.toString();*/
        return null;
    }

}
