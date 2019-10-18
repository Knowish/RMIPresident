package fr.univnantes.gui;

import fr.univnantes.impl.Card;
import fr.univnantes.impl.Player;
import fr.univnantes.rmi.inter.PlayerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class GameBoard {

    private Player player;
    private JPanel panel1;
    private JLabel player3Name;
    private JLabel player2Name;
    private JLabel player4Name;
    private JLabel myName;
    private JButton passButton;
    private JLabel turnPlayer3;
    private JLabel myTurn;
    private JLabel turnPlayer2;
    private JLabel turnPlayer4;
    private CardList cardList1;
    private List<PlayerInterface> opponents;

    public GameBoard(Player player) throws RemoteException {
        this.player = player;
        opponents = player.getOpponents();

        myName.setText(player.getUserName());
        player2Name.setText(opponents.get(0).getUserName());
        player3Name.setText(opponents.get(1).getUserName());
        player4Name.setText(opponents.get(2).getUserName());

        passButton.addActionListener(actionEvent -> {
            try {
                player.setMyTurn(false);
                player.setPassTurn(false);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void promptUserChoice() throws RemoteException {
        //default title and icon
        JOptionPane.showMessageDialog(panel1,
                "Do you want to pass?");
        player.pass();
    }

    public void displayWhosPlaying(){

        try {
            cardList1.updateCards(player.getHand());

            if(player.isMyTurn()){

                myTurn.setText("My turn");
                turnPlayer2.setText("");
                turnPlayer3.setText("");
                turnPlayer4.setText("");

            } else if (opponents.get(0).isMyTurn()){

                myTurn.setText("");
                turnPlayer2.setText("His turn");
                turnPlayer3.setText("");
                turnPlayer4.setText("");

            } else if (opponents.get(1).isMyTurn()){


                myTurn.setText("");
                turnPlayer2.setText("");
                turnPlayer3.setText("His turn");
                turnPlayer4.setText("");

            } else if (opponents.get(2).isMyTurn()) { //TODO:Cette ligne est que pour les tests, faudra l'enlever

                myTurn.setText("");
                turnPlayer2.setText("");
                turnPlayer3.setText("");
                turnPlayer4.setText("His turn");

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
