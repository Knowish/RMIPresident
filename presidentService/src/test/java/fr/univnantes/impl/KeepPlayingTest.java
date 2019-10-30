package fr.univnantes.impl;

import fr.univnantes.inter.PlayerInterface;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeepPlayingTest {

    @Test
    public void everyoneWantToPlayScenario() throws RemoteException, InterruptedException {
        PlayerInterface player1 = mock(PlayerInterface.class);
        PlayerInterface player2 = mock(PlayerInterface.class);
        PlayerInterface player3 = mock(PlayerInterface.class);
        PlayerInterface player4 = mock(PlayerInterface.class);
        when(player1.askKeepPlaying(1)).thenReturn(true);
        when(player2.askKeepPlaying(2)).thenReturn(true);
        when(player3.askKeepPlaying(3)).thenReturn(true);
        when(player4.askKeepPlaying(4)).thenReturn(true);

        List<PlayerInterface> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        KeepPlaying keepPlaying = new KeepPlaying(players);
        assertTrue(keepPlaying.continuePlaying());
    }

    @Test
    public void oneDoesntWantToPlayScenario() throws RemoteException, InterruptedException {
        PlayerInterface player1 = mock(PlayerInterface.class);
        PlayerInterface player2 = mock(PlayerInterface.class);
        PlayerInterface player3 = mock(PlayerInterface.class);
        PlayerInterface player4 = mock(PlayerInterface.class);
        when(player1.askKeepPlaying(1)).thenReturn(true);
        when(player2.askKeepPlaying(2)).thenReturn(false);
        when(player3.askKeepPlaying(3)).thenReturn(true);
        when(player4.askKeepPlaying(4)).thenReturn(true);

        List<PlayerInterface> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        KeepPlaying keepPlaying = new KeepPlaying(players);
        assertFalse(keepPlaying.continuePlaying());
    }

    @Test
    public void oneTimeoutScenario() throws RemoteException, InterruptedException {
        PlayerInterface player1 = mock(PlayerInterface.class);
        PlayerInterface player2 = mock(PlayerInterface.class);
        PlayerInterface player3 = mock(PlayerInterface.class);
        PlayerInterface player4 = mock(PlayerInterface.class);
        when(player1.askKeepPlaying(1)).then(new TimeoutAnswer());
        when(player2.askKeepPlaying(2)).thenReturn(true);
        when(player3.askKeepPlaying(3)).thenReturn(true);
        when(player4.askKeepPlaying(4)).thenReturn(true);

        List<PlayerInterface> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        KeepPlaying keepPlaying = new KeepPlaying(players);
        assertFalse(keepPlaying.continuePlaying());
    }

    private class TimeoutAnswer implements Answer {

        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            Thread.sleep(40000);
            return null;
        }
    }
}
