package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Lobby implements PropertyChangeListener {
    private JPanel lobby;
    private JLabel playersFoundCount;
    private JLabel nbWaitingPlayers;
    private Client client;

    public Lobby(Client client, Border border, CardLayout cl, JPanel cards) throws RemoteException {

            this.client = client;
            lobby.setBorder(border);
            nbWaitingPlayers.setText(Integer.toString(client.getGameServer().getNumberOfPendingPlayers()));

            //client.getMyPlayer().addPropertyChangeListener(this);
    }

    public JPanel getPanell() {
        return lobby;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        try {
            nbWaitingPlayers.setText(Integer.toString(client.getGameServer().getNumberOfPendingPlayers()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
