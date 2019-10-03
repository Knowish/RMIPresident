package fr.univnantes.rmi.gui;

import fr.univnantes.rmi.impl.Client;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                Client client = new Client();
                GuiBuilder frame = new GuiBuilder(client);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}
