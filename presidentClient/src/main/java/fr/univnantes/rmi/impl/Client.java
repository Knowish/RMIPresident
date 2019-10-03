package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.Server;
import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.UUID;

public class Client
{
    private Server gameServer;
    private UUID identifier;

    public Client() {
        identifier = UUID.randomUUID();
    }

    public boolean findGame(String username) {

        try {

            gameServer = (Server) Naming.lookup(
                    "//localhost:8080/serveurCardGame");
            //gameServer.addPropertyChangeListener((PmyPlayer);
            gameServer.apply(identifier, username);
            JOptionPane.showMessageDialog(null, "Connected to server as " + username);
            System.out.println("Connected to server as " + username);
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
            e.printStackTrace();
            return false;

        }
    }

    public Server getGameServer() { return gameServer; }

}
