package fr.univnantes.gui;

import javax.swing.*;

public class GameBoard {
    private JPanel panel1;
    private JButton buttonHelloWorld;

    public GameBoard() {

        buttonHelloWorld.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(null, "Hello world!");
        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

}
