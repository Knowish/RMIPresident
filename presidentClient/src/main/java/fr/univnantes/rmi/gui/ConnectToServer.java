package fr.univnantes.rmi.gui;
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

    public ConnectToServer(RmiClient client, Border border, CardLayout cl, JPanel cards) {

        panel1.setBorder(border);

        findAGameButton.addActionListener(actionEvent -> {

            String username = writeUsernameHereTextField.getText();

            if(client.findGame(username)){
                try {
                    Lobby lobbyView = new Lobby(client, border, cl, cards);
                    JPanel panel2 = lobbyView.getPanell();
                    cards.add(panel2, "Second Panel");
                    cl.next(cards);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

}

