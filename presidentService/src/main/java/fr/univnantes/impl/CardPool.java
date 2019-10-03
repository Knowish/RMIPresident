package fr.univnantes.impl;

import fr.univnantes.impl.Card;

import java.util.ArrayList;
import java.util.List;

public class CardPool {
    private List<Card> deck;

    public CardPool() {
        deck = new ArrayList<>();
        deck.add(new Card(3, "3 ♦"));
        deck.add(new Card(3, "3 ♣"));
        deck.add(new Card(3, "3 ♠"));
        deck.add(new Card(3, "3 ♥"));

        deck.add(new Card(4, "4 ♦"));
        deck.add(new Card(4, "4 ♣"));
        deck.add(new Card(4, "4 ♠"));
        deck.add(new Card(4, "4 ♥"));

        deck.add(new Card(5, "5 ♦"));
        deck.add(new Card(5, "5 ♣"));
        deck.add(new Card(5, "5 ♠"));
        deck.add(new Card(5, "5 ♥"));

        deck.add(new Card(6, "6 ♦"));
        deck.add(new Card(6, "6 ♣"));
        deck.add(new Card(6, "6 ♠"));
        deck.add(new Card(6, "6 ♥"));

        deck.add(new Card(7, "7 ♦"));
        deck.add(new Card(7, "7 ♣"));
        deck.add(new Card(7, "7 ♠"));
        deck.add(new Card(7, "7 ♥"));

        deck.add(new Card(8, "8 ♦"));
        deck.add(new Card(8, "8 ♣"));
        deck.add(new Card(8, "8 ♠"));
        deck.add(new Card(8, "8 ♥"));

        deck.add(new Card(9, "9 ♦"));
        deck.add(new Card(9, "9 ♣"));
        deck.add(new Card(9, "9 ♠"));
        deck.add(new Card(9, "9 ♥"));

        deck.add(new Card(10, "10 ♦"));
        deck.add(new Card(10, "10 ♣"));
        deck.add(new Card(10, "10 ♠"));
        deck.add(new Card(10, "10 ♥"));

        deck.add(new Card(11, "Valet ♦"));
        deck.add(new Card(11, "Valet ♣"));
        deck.add(new Card(11, "Valet ♠"));
        deck.add(new Card(11, "Valet ♥"));

        deck.add(new Card(12, "Dame ♦"));
        deck.add(new Card(12, "Dame ♣"));
        deck.add(new Card(12, "Dame ♠"));
        deck.add(new Card(12, "Dame ♥"));

        deck.add(new Card(13, "Roi ♦"));
        deck.add(new Card(13, "Roi ♣"));
        deck.add(new Card(13, "Roi ♠"));
        deck.add(new Card(13, "Roi ♥"));

        deck.add(new Card(14, "As ♦"));
        deck.add(new Card(14, "As ♣"));
        deck.add(new Card(14, "As ♠"));
        deck.add(new Card(14, "As ♥"));

        deck.add(new Card(15, "2 ♦"));
        deck.add(new Card(15, "2 ♣"));
        deck.add(new Card(15, "2 ♠"));
        deck.add(new Card(15, "2 ♥"));
    }

    public List<Card> getDeck() {
        return deck;
    }
}
