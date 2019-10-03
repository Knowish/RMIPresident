package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;

public class ConnectToServer {

    private JLabel welcomeToTheRMITextField;
    private JLabel pleaseChooseAUserTextArea;
    private JTextField writeUsernameHereTextField;
    private JButton findAGameButton;
    private JPanel panel1;

    public ConnectToServer(Client client) {

        findAGameButton.addActionListener(actionEvent -> {

            String username = writeUsernameHereTextField.getText();

            client.findGame(username);

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

}
