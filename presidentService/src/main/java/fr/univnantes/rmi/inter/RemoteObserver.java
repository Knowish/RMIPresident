package fr.univnantes.rmi.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

  void update (Object observable, Object updateMsg) throws RemoteException;

}
