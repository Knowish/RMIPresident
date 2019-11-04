package fr.univnantes.gui;

import fr.univnantes.impl.Card;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the hands of cards of the player
 */
public class CardList extends JPanel {

    public CardList() {
        super();
        setLayout(new GridLayout(1, 13));
}

    void updateCards(java.util.List<Card> cards) {
        for (Component comp : getComponents()) {
            remove(comp);
        }
        for (Card c : cards) {
            add(new JLabel(c.getName()));
        }
        repaint();
    }



}
