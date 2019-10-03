package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.Server;

import javax.swing.*;
import java.rmi.Naming;

public class Client {
    private Server gameServer;

    public void findGame(String username) {

        Player myPlayer = new Player(username);
        //myPlayer.setUserName(username);

        try {

            gameServer = (Server) Naming.lookup(
                    "//localhost:8080/serveurCardGame");

            //gameServer.apply(this);
            System.out.println("Connected to server as " + myPlayer.getUserName());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");

        }
    }
}
