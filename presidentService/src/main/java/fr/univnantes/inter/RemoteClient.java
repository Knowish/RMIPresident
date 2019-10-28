package fr.univnantes.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote {

  void update (Object observable, Object updateMsg) throws RemoteException;

}
