package fr.univnantes.rmi.client;

import fr.univnantes.rmi.ServerCardGame;

import java.rmi.Naming;

public class ClientCardGame {

    public static void main(String[] argv){

        try {

            ServerCardGame cardgame = (ServerCardGame) Naming.lookup(
                    "//localhost:8080/serveurCardGame");
            System.out.println(cardgame.affiche());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
