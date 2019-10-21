package fr.univnantes.impl;

import fr.univnantes.rmi.inter.PlayerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PresidentTest {

    Game gametest;

    @BeforeEach
    void setup() throws RemoteException {
        List<PlayerInterface> listplayer = new ArrayList<>();
        listplayer.add( new Player("Player1"));
        listplayer.add(new Player("Player2"));
        listplayer.add(new Player("Player3"));
        listplayer.add(new Player("Player4"));
        gametest = new President(listplayer);
    }

    @Test
    void playGameTest(){

    }
}
