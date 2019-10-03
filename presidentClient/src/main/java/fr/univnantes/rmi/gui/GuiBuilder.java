package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiBuilder extends JFrame{

  private final CardLayout cl = new CardLayout();
  private final JPanel cards = new JPanel(cl);
  private final Border border = BorderFactory.createEmptyBorder(200, 400, 200, 400);
  private Client client;

  public GuiBuilder(Client client) {

    JPanel contentPane = new JPanel();
    this.client = client;
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    ConnectToServer connectToServerView = new ConnectToServer(client, border, cl, cards);
    JPanel panel1 = connectToServerView.getPanel1();
    cards.add(panel1, "First Panel");

    contentPane.add(cards);

    cl.show(cards, "First Panel");
  }
}
