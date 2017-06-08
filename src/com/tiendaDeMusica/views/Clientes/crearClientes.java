package com.tiendaDeMusica.views.Clientes;

import com.tiendaDeMusica.DAO.interfaces.ClientesDAO;
import com.tiendaDeMusica.DAO.mysql.mysqlCliente;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Clientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class crearClientes {

    private String Nombre;
    private String Cedula;
    private String Apellidos;
    private String Telefono;
    private String Direccion;
    public JPanel panel1;
    private JTextField textField1Nombre;
    private JTextField textField2Apellido;
    private JTextField textField3Cedula;
    private JTextField textField4Telefono;
    private JTextField textField5Direccion;
    private JButton cancelarButton;
    private JButton crearButton;
    private JPanel crearClientes;

    public crearClientes() {

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nombre = textField1Nombre.getText().toString();
                Apellidos = textField3Cedula.getText().toString();
                Cedula = textField4Telefono.getText().toString();
                Telefono = textField5Direccion.getText().toString();
                Direccion = textField2Apellido.getText().toString();
                if(validar(Nombre, Apellidos, Cedula,Direccion)){
                    JOptionPane.showMessageDialog(null, "Campos Vacios");
                }else{
                    mysqlCliente clientes_nuevo = new mysqlCliente();
                    insertarCliente(clientes_nuevo, Nombre,Apellidos, Cedula,Telefono,Direccion);
                    JOptionPane.showMessageDialog(null, "El usuario "+Nombre +", se creo con exito");


                }
            }
        });
    }

    public boolean validar(String Nombre, String Apellidos, String Cedula, String Telefono){
        return (Nombre.equals("") && Apellidos.equals("") && Cedula.equals("") && Telefono.equals("")  && Direccion.equals(""))? true:false;

    }

    private static void insertarCliente(ClientesDAO clientesDAO, String Nombre,String Apellidos, String Cedula, String Telefono,String Direccion){
        Clientes clientes = new Clientes();
        clientes.setNombre(Nombre);
        clientes.setApellidos(Apellidos);
        clientes.setCedula(Cedula);
        clientes.setTelefono(Telefono);
        clientes.setDireccion(Direccion);

        try{
            clientesDAO.insertar(clientes);
            System.out.println("Gracias a Dios");
        }catch (Excepcion e){
            System.out.println(e.getMessage());
        }
    }

    public static void main() {
        JFrame frame = new JFrame("crearClientes");
        frame.setContentPane(new crearClientes().crearClientes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}