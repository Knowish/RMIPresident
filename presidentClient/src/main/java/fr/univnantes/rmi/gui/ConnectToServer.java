package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ConnectToServer {

    private JLabel welcomeToTheRMITextField;
    private JLabel pleaseChooseAUserTextArea;
    private JTextField writeUsernameHereTextField;
    private JButton findAGameButton;
    private JPanel panel1;

    public ConnectToServer(Client client, Border border, CardLayout cl, JPanel cards) {

        panel1.setBorder(border);

        findAGameButton.addActionListener(actionEvent -> {

            String username = writeUsernameHereTextField.getText();

            if(client.findGame(username)){
                cl.next(cards);
            }

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

}

