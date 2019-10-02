package fr.univnantes.rmi.client;

import fr.univnantes.rmi.server.Server;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Client implements Serializable {
    protected Server gameServer;
    private String name;

    public Client(String name, Server server) {
        this.name = name;
        this.gameServer = server;
    }

    public Client() {}

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
