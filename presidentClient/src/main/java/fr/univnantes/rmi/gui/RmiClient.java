package fr.univnantes.rmi.gui;
import fr.univnantes.rmi.inter.RemoteObserver;
import fr.univnantes.rmi.inter.RmiService;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class RmiClient extends UnicastRemoteObject implements RemoteObserver {

  private RmiService remoteService;
  private UUID identifier;
  private String username;
  private int waitingPlayers;
  private PropertyChangeSupport support;

  protected RmiClient() throws RemoteException {
    super();
    support = new PropertyChangeSupport(this);
    identifier = UUID.randomUUID();
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
      GuiBuilder frame = new GuiBuilder(client);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);


    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public boolean findGame(String username) {

    try {

      this.username = username;

      remoteService = (RmiService) Naming
              .lookup("//localhost:9999/RmiService");

      remoteService.addObserver(this, identifier, username);
      JOptionPane.showMessageDialog(null, "Connected to server as " + username);
      System.out.println("Connected to server as " + username);
      return true;

    } catch (Exception e) {

      JOptionPane.showMessageDialog(null, "Impossible to connect to server, please try again");
      e.printStackTrace();
      return false;

    }
  }

  @Override
  public void update(Object observable, Object updateMsg)
          throws RemoteException {
    System.out.println("Received a signal from the server");
    setWaitingPlayers((Integer) updateMsg);
  }

  public RmiService getRemoteService() {
    return remoteService;
  }

  public void setWaitingPlayers(int value) {
    support.firePropertyChange("waitingPlayers", this.waitingPlayers, value);
    this.waitingPlayers = value;
  }

  public String getUsername(){
    return this.username;
  }
}
