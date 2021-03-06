package fr.univnantes.gui;

import fr.univnantes.impl.Card;
import fr.univnantes.impl.Player;
import fr.univnantes.inter.PlayerInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the view a player get when playing the game.
 */
public class GameBoard extends JFrame{

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
    private CardList trick;
    private List<PlayerInterface> opponents;
    private String namePlayer2;
    private String namePlayer3;
    private String namePlayer4;

    GameBoard(Player player) throws RemoteException {
        this.player = player;
        opponents = player.getOpponents();

        myName.setText(player.getUserName());
        namePlayer2 = opponents.get(0).getUserName();
        namePlayer3 = opponents.get(1).getUserName();
        namePlayer4 = opponents.get(2).getUserName();

        player2Name.setText(namePlayer2 + ", number of cards left : " + opponents.get(0).getHand().size());
        player3Name.setText(namePlayer3 + ", number of cards left : " + opponents.get(1).getHand().size());
        player4Name.setText(namePlayer4 + ", number of cards left : " + opponents.get(2).getHand().size());

        passButton.addActionListener(actionEvent -> {
            player.setMyTurn(false);
            player.setPassTurn(false);
        });
    }

    JPanel getPanel1() {
        return panel1;
    }

    public String promptCardChoice(List<Card> cardsICanPlay, String windowTitle) {

        String result = "";
        if(!cardsICanPlay.isEmpty()) {
            List<String> cardsName = new ArrayList<>();
            for (Card card : cardsICanPlay) {
                cardsName.add(card.getName());
            }

            Object[] possibilities = cardsName.toArray();

            result = (String) JOptionPane.showInputDialog(
                    panel1,
                    windowTitle,
                    "Choose a card",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);
        } else {
            JOptionPane.showMessageDialog(panel1, "You can't play. :/");
        }
        return result;
    }

    public void updateCards(List<Card> cards) {
        cardList1.updateCards(cards);
    }

    public void displayWhosPlaying(){

        try {

            player2Name.setText(namePlayer2 + ", number of cards left : " + opponents.get(0).getHand().size());
            player3Name.setText(namePlayer3 + ", number of cards left : " + opponents.get(1).getHand().size());
            player4Name.setText(namePlayer4 + ", number of cards left : " + opponents.get(2).getHand().size());

            String imdead, p2dead, p3dead, p4dead;

            imdead = player.isPassTurn() ? "PASSED" : "";
            p2dead = opponents.get(0).isPassTurn() ? "PASSED" : "";
            p3dead = opponents.get(1).isPassTurn() ? "PASSED" : "";
            p4dead = opponents.get(2).isPassTurn() ? "PASSED" : "";

            if(player.isMyTurn()){

                myTurn.setText("My turn");
                turnPlayer2.setText(p2dead);
                turnPlayer3.setText(p3dead);
                turnPlayer4.setText(p4dead);

            } else if (opponents.get(0).isMyTurn()){

                myTurn.setText(imdead);
                turnPlayer2.setText("His turn");
                turnPlayer3.setText(p3dead);
                turnPlayer4.setText(p4dead);

            } else if (opponents.get(1).isMyTurn()){


                myTurn.setText(imdead);
                turnPlayer2.setText(p2dead);
                turnPlayer3.setText("His turn");
                turnPlayer4.setText(p4dead);

            } else if (opponents.get(2).isMyTurn()) {

                myTurn.setText(imdead);
                turnPlayer2.setText(p2dead);
                turnPlayer3.setText(p3dead);
                turnPlayer4.setText("His turn");

            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void setTrick(Card card) {
        List<Card> listTas = new ArrayList<>();
        listTas.add(card);
        trick.updateCards(listTas);

    }

    private String getLabelCorrespondingToRole(int rank){
        String labelOfTheRoleToExchangeTo="";
        switch (rank){
            case 1:
                labelOfTheRoleToExchangeTo = "président";
                break;
            case 2:
                labelOfTheRoleToExchangeTo = "vice-président";
                break;
            case 3:
                labelOfTheRoleToExchangeTo = "vice-trou du cul";
                break;
            case 4:
                labelOfTheRoleToExchangeTo = "trou du cul";
                break;
            default:
                break;
        }
        return labelOfTheRoleToExchangeTo;
    }

    public void showUserExchangedCards(Card exchangedCard, int roleToExchangeTo) {
        String labelOfTheRoleToExchangeTo = getLabelCorrespondingToRole(roleToExchangeTo);
        JOptionPane.showMessageDialog(panel1, "You are going to exchange this card with the "+labelOfTheRoleToExchangeTo+" : "+exchangedCard.getName());
    }

    public boolean askKeepPlaying(int rank) {
        String rankTitle = getLabelCorrespondingToRole(rank);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Round Over! You are "+rankTitle+". Would you like to keep playing?","Keep playing?",dialogButton);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}
