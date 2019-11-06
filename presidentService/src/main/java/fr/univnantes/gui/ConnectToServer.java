package fr.univnantes.gui;
import fr.univnantes.impl.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * This class is the view the client get when he launches the application.
 * It is an invite to connect to the server.
 */
public class ConnectToServer {

    private JTextField writeUsernameHereTextField;
    private JButton findAGameButton;
    private JPanel panelConnectToServer;
    private JLabel welcomeToTheRMITextField;
    private JLabel pleaseChooseAUserTextArea;

    ConnectToServer(Player player, Border border, CardLayout cl, JPanel cards) {

        panelConnectToServer.setBorder(border);

        findAGameButton.addActionListener(actionEvent -> {

            String username = writeUsernameHereTextField.getText();
            welcomeToTheRMITextField.setText("Welcome to the RMI president game!");
            pleaseChooseAUserTextArea.setText("Please choose a user  name and find a game!");
            player.setUserName(username);
            Lobby lobbyView = new Lobby(player, border, cl, cards);
            player.setLobby(lobbyView);
            player.setConnectToServerView(this);
            JPanel panel = lobbyView.getPanell();
            cards.add(panel, "Second Panel");

            if(player.findGame()){

                cl.next(cards);

            }

        });

    }

    JPanel getPanelConnectToServer() {
        return panelConnectToServer;
    }

    JButton getFindAGameButton() {
        return findAGameButton;
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(panelConnectToServer, message);
    }
}

