package fr.univnantes.gui;

import fr.univnantes.impl.Card;

import javax.swing.*;
import java.awt.*;

public class CardList extends JPanel {

    public CardList() {
        super();
        setLayout(new GridLayout(1, 13));
}

    public void updateCards(java.util.List<Card> cards) {
        for (Component comp : getComponents()) {
            remove(comp);
        }
        for (Card c : cards) {
            add(new JLabel(c.getName()));
        }
        repaint();
    }



}
