package fr.univnantes.rmi.impl;

import fr.univnantes.impl.Card;
import fr.univnantes.impl.Game;
import fr.univnantes.impl.Player;
import fr.univnantes.rmi.inter.RemoteObserver;
import fr.univnantes.rmi.inter.RmiService;

import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class RmiServer extends Observable implements RmiService {

  private Game game;
  private Queue<Player> pendingPlayers;
  private int numberOfPendingPlayers;

  private class WrappedObserver implements Observer, Serializable {

    private static final long serialVersionUID = 1L;

    private RemoteObserver ro = null;

    public WrappedObserver(RemoteObserver ro) {
      this.ro = ro;
    }

    @Override
    public void update(Observable o, Object arg) {
      try {
        ro.update(o.toString(), arg);
      } catch (RemoteException e) {
        System.out
                .println("Remote exception removing observer:" + this);
        o.deleteObserver(this);
      }
    }

  }

  @Override
  /*synchronized*/ public void addObserver(RemoteObserver o, UUID identifier, String username) throws RemoteException {
    WrappedObserver mo = new WrappedObserver(o);
    addObserver(mo);
    System.out.println("Added observer:" + mo);

    Player player = new Player(identifier, username);
    pendingPlayers.add(player);
    numberOfPendingPlayers ++ ;
    if (pendingPlayers.size() >= 4) {
      List<Player> startingPlayers = new ArrayList<>();
      for (int i = 0; i < 4; ++i) {
        startingPlayers.add(pendingPlayers.remove());
      }
      game = new Game(startingPlayers);
    }
    setChanged();
    notifyObservers(numberOfPendingPlayers);
  }

  @Override
  public int getNumberOfPendingPlayers() throws RemoteException {
    return numberOfPendingPlayers;
  }

  public RmiServer() {
    pendingPlayers= new ArrayDeque<>();
    numberOfPendingPlayers=0;
    thread.start();
  }

  Thread thread = new Thread() {
    @Override
    public void run() {
      while (true) {
      }
    };
  };

  private static final long serialVersionUID = 1L;

  public static void main(String[] args) {
    try {
      Registry rmiRegistry = LocateRegistry.createRegistry(9999);
      RmiService rmiService = (RmiService) UnicastRemoteObject
              .exportObject(new RmiServer(), 9999);
      rmiRegistry.bind("RmiService", rmiService);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
