package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.Server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ServerImpl extends UnicastRemoteObject implements Server {

    private Game game;
    private Queue<Player> pendingPlayers;
    private PropertyChangeSupport support;
    private int numberOfPendingPlayers;

    public ServerImpl() throws RemoteException {
        pendingPlayers= new ArrayDeque<>();
        support = new PropertyChangeSupport(this);
        numberOfPendingPlayers=0;
    }

    @Override
    public void apply(Player client) throws RemoteException {

        pendingPlayers.add(client);
        setNumberOfPendingPlayers(numberOfPendingPlayers+1);
        if (pendingPlayers.size() >= 4) {
            List<Player> startingPlayers = new ArrayList<>();
            for (int i = 0; i < 4; ++i) {
                startingPlayers.add(pendingPlayers.remove());
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
        StringBuilder res = new StringBuilder();
        for (Player c : pendingPlayers) {
            res.append(c.getUserName()).append(" is waiting for a game\n");
        }
        return res.toString();
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setNumberOfPendingPlayers(int value){
        support.firePropertyChange("numberOfPendingPlayers", this.numberOfPendingPlayers, value);
        this.numberOfPendingPlayers = value;
    }

}
