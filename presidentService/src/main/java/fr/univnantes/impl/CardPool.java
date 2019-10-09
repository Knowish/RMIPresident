package fr.univnantes.impl;

import java.util.ArrayList;
import java.util.List;

import static fr.univnantes.impl.ConstUtils.*;

public class CardPool {
    private List<Card> deck;

    public CardPool() {
        deck = new ArrayList<>();
        generateCardPool();
    }

    public List<Card> getDeck() {
        return deck;
    }

    private void generateCardPool() {

        String colors[] = {SPADE, HEART, DIAMOND, CLUB};

        for (String color : colors) {
            for  (int j = MIN_VALUE_CARD; j < NUMBER_CARDS_PER_COLOR + MIN_VALUE_CARD; j++) { // j : 3 -> 15
                deck.add(new Card(j, matchValueToName(j) + " " + color));
            }
        }
    }

    private String matchValueToName(int value) {

        String name;

        switch (value) {
            case 11:
                name = JACK;
                break;
            case 12:
                name = QUEEN;
                break;
            case 13:
                name = KING;
                break;
            case 14:
                name = ACE;
                break;
            case 15:
                name = "2";
                break;
            default:
                name = String.valueOf(value);
                break;
        }
        return name;
    }
}
