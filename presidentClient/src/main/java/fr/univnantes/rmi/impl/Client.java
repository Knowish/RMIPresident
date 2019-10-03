/*package fr.univnantes.rmi.impl;

import fr.univnantes.rmi.inter.RemoteObserver;
import fr.univnantes.rmi.inter.RmiService;
import javax.swing.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Client extends UnicastRemoteObject implements RemoteObserver
{
    private RmiService gameServer;
    private UUID identifier;

    public Client() throws RemoteException {
        identifier = UUID.randomUUID();
    }

    public boolean findGame(Client client, String username) {

        try {

            gameServer = (RmiService) Naming.lookup(
                    "//localhost:8080/serveurCardGame");
            gameServer.addObserver(client);
            //gameServer.apply(identifier, username);
            JOptionPane.showMessageDialog(null, "Connected to server as " + username);
            System.out.println("Connected to server as " + username);
            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
            e.printStackTrace();
            return false;

        }
    }

    public RmiService getGameServer() { return gameServer; }

    public static void main(String[] args) {
        try {
            RmiService remoteService = (RmiService) Naming
                    .lookup("//localhost:8080/serveurCardGame");

            Client client = new Client();
            remoteService.addObserver(client);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException {
        System.out.println("got message:" + updateMsg);
    }

}*/
