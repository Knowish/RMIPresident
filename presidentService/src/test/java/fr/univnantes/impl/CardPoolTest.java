/*package fr.univnantes.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.Assert.*;

class CardPoolTest {

    private CardPool pool;

    @BeforeEach
    void setup() {
        pool = new CardPool();
    }

    @Test
    void poolContentTest() {
        assertEquals(52, pool.getBoard().size());

        for (Card card : pool.getBoard()) {
            System.out.println(card);
        }

        Collections.shuffle(pool.getBoard());
        System.out.println("\n\nSHUFFLE\n\n");

        for (Card card : pool.getBoard()) {
            System.out.println(card);
        }
    }
}*/
