package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.Server;
import javax.swing.*;
import java.rmi.Naming;

public class Client {
    private Server gameServer;

    private Player myPlayer;

    public Client(){
        myPlayer = new Player();
    }

    public boolean findGame(String username) {

        myPlayer.setUserName(username);

        try {

            gameServer = (Server) Naming.lookup(
                    "//localhost:8080/serveurCardGame");

            JOptionPane.showMessageDialog(null, "Connected to server as " + myPlayer.getUserName());
            gameServer.addPropertyChangeListener(myPlayer);
            gameServer.apply(myPlayer);
            System.out.println("Connected to server as " + myPlayer.getUserName());
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
            return false;

        }
    }

    public Player getMyPlayer() {
        return myPlayer;
    }
}
