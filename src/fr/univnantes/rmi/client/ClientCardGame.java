package fr.univnantes.rmi.client;

import fr.univnantes.rmi.server.Server;

import java.rmi.Naming;

public class ClientCardGame {

    public static void main(String[] argv){

        try {

            Server cardgame = (Server) Naming.lookup(
                    "//localhost:8080/serveurCardGame");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
