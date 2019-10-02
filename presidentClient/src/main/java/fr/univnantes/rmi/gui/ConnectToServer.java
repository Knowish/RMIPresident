package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;

public class ConnectToServer {

    private JTextField welcomeToTheRMITextField;
    private JTextArea pleaseChooseAUserTextArea;
    private JTextField writeUsernameHereTextField;
    private JButton findAGameButton;
    private JPanel pannel1;

    public ConnectToServer(Client client) {

        findAGameButton.addActionListener(actionEvent -> {

            String username = writeUsernameHereTextField.getText();

            client.findGame(username);

        });

    }

    public JPanel getPannel1() {
        return pannel1;
    }

}
