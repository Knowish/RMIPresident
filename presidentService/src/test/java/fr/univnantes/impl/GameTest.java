package fr.univnantes.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new President();
    }

    @Test
    void distributionTest(){}
}
