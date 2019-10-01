package fr.univnantes.rmi.server;

import fr.univnantes.rmi.ServerCardGame;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestServerCardGame {

    public static void main(String[] argv){

        try{

            LocateRegistry.createRegistry(8080);
            ServerCardGame serveurCardGame = new ServerCardGame();
            Naming.bind("//localhost:8080/serveurCardGame",serveurCardGame);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
