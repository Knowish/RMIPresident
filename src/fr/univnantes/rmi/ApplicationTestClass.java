package fr.univnantes.rmi;

import fr.univnantes.rmi.client.Client;
import fr.univnantes.rmi.client.Player;
import fr.univnantes.rmi.server.Server;
import fr.univnantes.rmi.server.TestServerCardGame;

import java.rmi.Naming;
import java.util.ArrayList;

public class ApplicationTestClass {
    public static void main(String[] args) {
        TestServerCardGame.main(new String[]{});

        try {
            Server cardgame = (Server) Naming.lookup("//localhost:8080/serveurCardGame");
            Player client = new Player("Player1", cardgame);
            Player client2 = new Player("Player2", cardgame);
            Player client3 = new Player("Player3", cardgame);
            Player client4 = new Player("Player4", cardgame);
            client.findGame();
            client2.findGame();
            client3.findGame();
            client4.findGame();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
