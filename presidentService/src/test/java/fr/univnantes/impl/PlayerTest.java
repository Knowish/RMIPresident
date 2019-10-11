package fr.univnantes.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;


import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setup(){
        try {
            player = new Player( "Player_de_base");
        }catch(Exception e){

        }
    }

    @Test
    void creationPlayerTest() throws RemoteException {
        assertEquals("Player_de_base",player.getUserName());
        assertTrue(player.getHand().isEmpty());
        assertTrue(player.getOpponents().isEmpty());
        Player opponent = new Player();
        opponent.setUserName("Tadaronne");
        player.addOpponent(opponent);
        assertEquals("Tadaronne",player.getOpponents().get(0).getUserName());
    }

    @Test
    void addPlayCardTest() throws RemoteException, InterruptedException {
        Card cardtest = new Card(3,"Coeur");
        player.addToHand(cardtest);
        assertFalse(player.getHand().isEmpty());
        assertTrue(player.getHand().get(0).equals(cardtest));
        player.playCard(player.getHand().get(0));
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    void chooseCardTest() throws RemoteException{

    }

    @Test
    void passTurnTest()throws RemoteException{
        player.pass();
        assertTrue(player.isPassTurn());
        player.setPassTurn(false);
        assertFalse(player.isPassTurn());

    }

    @Test
    void myTurnTest()throws RemoteException{

        assertFalse(player.isMyTurn());
        player.setMyTurn(true);
        assertTrue(player.isMyTurn());
    }

}
