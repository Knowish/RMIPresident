package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new President();
    }

    @Test
    void cardPoolTest(){
        game.generateCardPool();
        assertEquals(52,game.board.size());
    }

    @Test
    void distributionTest() throws RemoteException {
        PlayerInterface player1 = new Player("Player1");
        PlayerInterface player2 = new Player("Player1");
        PlayerInterface player3 = new Player("Player1");
        PlayerInterface player4 = new Player("Player1");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.generateCardPool();
        game.distribution();
        assertTrue(game.getBoard().isEmpty());

        for(PlayerInterface p : game.getPlayers())
            assertEquals(13,p.getHand().size());

    }
}
