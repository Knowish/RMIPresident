package fr.univnantes.gui;
import fr.univnantes.impl.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.rmi.RemoteException;

public class ConnectToServer {

    private JLabel welcomeToTheRMITextField;
    private JLabel pleaseChooseAUserTextArea;
    private JTextField writeUsernameHereTextField;
    private JButton findAGameButton;
    private JPanel panel1;

    public ConnectToServer(Player player, Border border, CardLayout cl, JPanel cards) {

        panel1.setBorder(border);

        findAGameButton.addActionListener(actionEvent -> {

            try {

                String username = writeUsernameHereTextField.getText();
                player.setUserName(username);
                Lobby lobbyView = new Lobby(player, border, cl, cards);
                player.setLobby(lobbyView);
                JPanel panel2 = lobbyView.getPanell();
                cards.add(panel2, "Second Panel");

            } catch (RemoteException e) {

                e.printStackTrace();

            }

            if(player.findGame()){

                cl.next(cards);

            }

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

}

