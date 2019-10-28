package fr.univnantes.rmi.impl;
import fr.univnantes.impl.Player;
import fr.univnantes.inter.RemoteClient;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends UnicastRemoteObject implements RemoteClient {

    private int waitingPlayers;
    private PropertyChangeSupport support;

    protected RmiClient() throws RemoteException {
        super();
        support = new PropertyChangeSupport(this);
    }

//    public void addPropertyChangeListener(PropertyChangeListener pcl) {
//        support.addPropertyChangeListener(pcl);
//    }
//
//    public void removePropertyChangeListener(PropertyChangeListener pcl) {
//        support.removePropertyChangeListener(pcl);
//    }

    public static void main(String[] args) {

        try {

            RmiClient client = new RmiClient();
            Player myPlayer = new Player();
            myPlayer.run();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
        System.out.println("Received a signal from the server");
        setWaitingPlayers((Integer) updateMsg);

    }

    public void setWaitingPlayers(int value) {
        support.firePropertyChange("waitingPlayers", this.waitingPlayers, value);
        this.waitingPlayers = value;
    }

}
