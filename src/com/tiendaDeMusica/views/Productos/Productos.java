package com.tiendaDeMusica.views.Productos;

import javax.swing.*;

public class Productos {
    private JPanel Productos;
    private JPanel productos;
    private JComboBox comboBox1Precio;
    private JComboBox comboBox2Genero;
    private JComboBox comboBox3Artistas;
    private JButton button1Comprar;
    private JButton cancelarButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Productos");
        frame.setContentPane(new Productos().productos);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

