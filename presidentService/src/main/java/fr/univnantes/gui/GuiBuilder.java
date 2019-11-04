package fr.univnantes.gui;

import fr.univnantes.impl.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * this class is used to instantiate and build the GUI
 */
public class GuiBuilder extends JFrame{

    public GuiBuilder(Player player) {

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        CardLayout cl = new CardLayout();
        JPanel cards = new JPanel(cl);
        Border border = BorderFactory.createEmptyBorder(200, 400, 200, 400);
        ConnectToServer connectToServerView = new ConnectToServer(player, border, cl, cards);
        JPanel panel1 = connectToServerView.getPanelConnectToServer();
        cards.add(panel1, "First Panel");

        contentPane.add(cards);

        getRootPane().setDefaultButton(connectToServerView.getFindAGameButton());

        cl.show(cards, "First Panel");
    }


}
