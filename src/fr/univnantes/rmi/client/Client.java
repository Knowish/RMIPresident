package fr.univnantes.rmi.client;

import fr.univnantes.rmi.server.Server;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.*;
import java.rmi.Naming;

public class Client implements Serializable {
    protected Server gameServer;
    private String name;

    public Client(String name, Server server) {
        this.name = name;
        this.gameServer = server;
    }

    public Client() {}

    public void findGame(String username) {

        Player myPlayer = new Player(username, gameServer);
        myPlayer.setUserName(username);

        try {

            gameServer = (Server) Naming.lookup(
                    "//localhost:8080/serveurCardGame");

            //gameServer.apply(this);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");

        }
    }
    
    public void findGame() {
        try {
            gameServer.apply(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
