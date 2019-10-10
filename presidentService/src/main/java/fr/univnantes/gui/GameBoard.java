package fr.univnantes.gui;

import fr.univnantes.impl.Player;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

public class GameBoard {

    private Player player;
    private JPanel panel1;
    private JLabel player3Name;
    private JLabel player2Name;
    private JLabel player4Name;
    private JLabel myName;

    public GameBoard(Player player) throws RemoteException {
        this.player = player;
        List<String> opponentsNames = player.getOpponentsNames();

        myName.setText(player.getUserName());
        player2Name.setText(opponentsNames.get(0));
        player3Name.setText(opponentsNames.get(1));
        player4Name.setText(opponentsNames.get(2));

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

}
