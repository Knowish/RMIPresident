package fr.univnantes.gui;

import fr.univnantes.gui.ConnectToServer;
import fr.univnantes.impl.Player;
import fr.univnantes.rmi.inter.PlayerInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GuiBuilder extends JFrame{

  private final CardLayout cl = new CardLayout();
  private final JPanel cards = new JPanel(cl);
  private final Border border = BorderFactory.createEmptyBorder(200, 400, 200, 400);

  public GuiBuilder(Player player) {

    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    ConnectToServer connectToServerView = new ConnectToServer(player, border, cl, cards);
    JPanel panel1 = connectToServerView.getPanel1();
    cards.add(panel1, "First Panel");

    contentPane.add(cards);

    getRootPane().setDefaultButton(connectToServerView.getFindAGameButton());

    cl.show(cards, "First Panel");
  }
}
