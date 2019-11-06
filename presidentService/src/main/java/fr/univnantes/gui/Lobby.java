package fr.univnantes.gui;
import fr.univnantes.impl.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.rmi.RemoteException;

/**
 * This class is used to represent the lobby, where the player will wait until the game is created
 */
public class Lobby {
    private JPanel lobby;
    private JLabel playersFoundCount;
    private JLabel nbWaitingPlayers;
    private JLabel usernameDisplayed;
    private Player player;
    private CardLayout cl;
    private JPanel cards;

    Lobby(Player player, Border border, CardLayout cl, JPanel cards) {

        this.player = player;
        this.cl = cl;
        this.cards = cards;
        usernameDisplayed.setText(player.getUserName());
        lobby.setBorder(border);
        playersFoundCount.setText("Players found :");
        // Pour retirer l'action de "Entrée" une fois le lobby passé (reste pour le choix de cartes)
        ((JRootPane)this.cards.getParent().getParent().getParent()).setDefaultButton(null);

    }

    JPanel getPanell() {
        return lobby;
    }

    public void updateTextWaitingPlayer(String waitingPlayersNumber){
        nbWaitingPlayers.setText(waitingPlayersNumber);
    }

    public void changeViewToBoardgame() throws RemoteException {

        System.out.println("Je crée la gameBoard");
        GameBoard gameboardView = new GameBoard(player);
        player.setGameBoard(gameboardView);
        JPanel nextPanel = gameboardView.getPanel1();
        cards.add(nextPanel, "Third Panel");
        cl.next(cards);
    }
}
