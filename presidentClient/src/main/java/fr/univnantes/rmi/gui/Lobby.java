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
    private JLabel usernameDisplayed;
    private RmiClient client;
    private int nbPendingPlayers;
    private CardLayout cl;
    private JPanel cards;

    public Lobby(RmiClient client, Border border, CardLayout cl, JPanel cards) throws RemoteException {

            this.client = client;
            this.cl = cl;
            this.cards = cards;
            usernameDisplayed.setText(client.getUsername());
            lobby.setBorder(border);
            client.addPropertyChangeListener(this);
            nbWaitingPlayers.setText(Integer.toString(client.getRemoteService().getNumberOfPendingPlayers()));

    }

    public JPanel getPanell() {
        return lobby;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        nbPendingPlayers = (Integer)evt.getNewValue();
        nbWaitingPlayers.setText(Integer.toString(nbPendingPlayers));
    }

    public void createGameBoardView(){
        GameBoard gameboardView = new GameBoard();
        JPanel nextPanel = gameboardView.getPanel1();
        cards.add(nextPanel, "Third Panel");
        cl.next(cards);
    }
}
