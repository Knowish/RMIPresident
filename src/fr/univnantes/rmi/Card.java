package fr.univnantes.rmi;

import java.io.Serializable;

public class Card  implements Serializable {
    private int value;
    private String name;

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
