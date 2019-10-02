package fr.univnantes.rmi;

import fr.univnantes.rmi.impl.ServerImpl;
import fr.univnantes.rmi.inter.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestServerCardGame {

    public static void main(String[] argv){

        try{

            LocateRegistry.createRegistry(8080);
            Server serveurCardGame = new ServerImpl();
            Naming.bind("//localhost:8080/serveurCardGame",serveurCardGame);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
