package fr.univnantes.impl;


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
        assertEquals(52, pool.getDeck().size());

        for (Card card : pool.getDeck()) {
            System.out.println(card);
        }

        Collections.shuffle(pool.getDeck());
        System.out.println("\n\nSHUFFLE\n\n");

        for (Card card : pool.getDeck()) {
            System.out.println(card);
        }
    }
}
