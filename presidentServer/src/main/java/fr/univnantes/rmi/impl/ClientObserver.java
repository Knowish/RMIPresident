package fr.univnantes.rmi.impl;

import fr.univnantes.inter.PlayerInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

/**
 * define an observer. It is used to notify the pending players when a new player is
 * joining the game.
 */
public class ClientObserver implements Observer, Serializable {

    private static final long serialVersionUID = 1L;

    private PlayerInterface ro;

    ClientObserver(PlayerInterface ro) {
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