package fr.univnantes.rmi.impl;
import fr.univnantes.gui.GuiBuilder;
import fr.univnantes.impl.Player;
import fr.univnantes.rmi.inter.RemoteClient;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends UnicastRemoteObject implements RemoteClient {

  private int waitingPlayers;
  private PropertyChangeSupport support;

  protected RmiClient() throws RemoteException {
    super();
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  public static void main(String[] args) {

    try {

      RmiClient client = new RmiClient();
      Player myPlayer = new Player();
      GuiBuilder frame = new GuiBuilder(myPlayer);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);


    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void update(Object observable, Object updateMsg)
          throws RemoteException {
    System.out.println("Received a signal from the server");
    setWaitingPlayers((Integer) updateMsg);

  }

  public void setWaitingPlayers(int value) {
    support.firePropertyChange("waitingPlayers", this.waitingPlayers, value);
    this.waitingPlayers = value;
  }

}
