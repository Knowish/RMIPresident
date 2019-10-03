package fr.univnantes.rmi.gui;
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
    private RmiClient client;

    public Lobby(RmiClient client, Border border, CardLayout cl, JPanel cards) throws RemoteException {

            this.client = client;
            lobby.setBorder(border);
            client.addPropertyChangeListener(this);
            nbWaitingPlayers.setText(Integer.toString(client.getRemoteService().getNumberOfPendingPlayers()));

    }

    public JPanel getPanell() {
        return lobby;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        nbWaitingPlayers.setText(Integer.toString((Integer)evt.getNewValue()));
    }
}
