package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        JFrame frameConnectToServer = new JFrame("");
        Client client = new Client();

        frameConnectToServer.setContentPane(new ConnectToServer(client).getPanel1());
        frameConnectToServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConnectToServer.pack();
        frameConnectToServer.setVisible(true);

    }
}
