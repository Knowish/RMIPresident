package fr.univnantes.impl;

import java.io.Serializable;

public class Card  implements Serializable, Comparable<Card> {
    private int value;
    private String name;

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card " + name + ", value: "+ value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Card card = (Card) obj;
        return card.value == this.value
                && card.name.equals(this.name);
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.value, o.value);
    }
}
