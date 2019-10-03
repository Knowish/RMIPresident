package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Lobby {
    private JPanel lobby;
    private JLabel playersFoundCount;
    private JLabel nbWaitingPlayers;
    private Client client;

    public Lobby(Client client, Border border, CardLayout cl, JPanel cards){
        this.client = client;
        lobby.setBorder(border);
        nbWaitingPlayers.setText(Integer.toString(client.getMyPlayer().getNumberOfPendingPlayers()));
    }

    public JPanel getPanell() {
        return lobby;
    }

}
