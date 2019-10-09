package fr.univnantes.gui;
import fr.univnantes.impl.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class Lobby /*implements PropertyChangeListener*/ {
    private JPanel lobby;
    private JLabel playersFoundCount;
    private JLabel nbWaitingPlayers;
    private JLabel usernameDisplayed;
    //private RmiClient client;
    private Player player;
    private int nbPendingPlayers;
    private CardLayout cl;
    private JPanel cards;

    public Lobby(Player player, Border border, CardLayout cl, JPanel cards) throws RemoteException {

            //this.client = client;
        this.player = player;
            this.cl = cl;
            this.cards = cards;
            usernameDisplayed.setText(player.getUserName());
            lobby.setBorder(border);
            //client.addPropertyChangeListener(this);
            //client.getMyPlayer().addPropertyChangeListener(this);
            //nbWaitingPlayers.setText(Integer.toString(player.getWaitingPlayers()));

    }

    public JPanel getPanell() {
        return lobby;
    }

    /*
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case "waitingPlayers" : {
                nbPendingPlayers = (Integer) evt.getNewValue();
                nbWaitingPlayers.setText(Integer.toString(nbPendingPlayers));
                break;
            }

            case "gameInit" : {
                System.out.println("Je crée ma troisième vue");
                createGameBoardView();
                break;
            }
        }

    }*/

    public void updateTextWaitingPlayer(String waitingPlayersNumber){
        nbWaitingPlayers.setText(waitingPlayersNumber);
    }

    public void createGameBoardView(){
        System.out.println("Je crée la gameBoard");
        GameBoard gameboardView = new GameBoard();
        JPanel nextPanel = gameboardView.getPanel1();
        cards.add(nextPanel, "Third Panel");
        cl.next(cards);
    }
}
