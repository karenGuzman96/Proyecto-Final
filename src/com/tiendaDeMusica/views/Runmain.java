package com.tiendaDeMusica.views;


import com.tiendaDeMusica.views.Clientes.crearClientes;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runmain{

    private JPanel Jpanel;
    private JButton crearClienteButton;
    private JButton estadisticaButton;
    private JButton facturaButton;
    private JPanel runmain;

    public Runmain() {

        crearClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                crearClientes.main();
                runmain.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Runmain");
        frame.setContentPane(new Runmain().runmain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}