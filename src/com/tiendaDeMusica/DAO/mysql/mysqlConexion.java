package com.tiendaDeMusica.DAO.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlConexion {
    Connection conectar() throws SQLException {
        Connection conexion = null;
        String url = "jdbc:mysql://127.0.0.1:3306/tienda de musica";
        String usuario = "root";
        String contrasena = "";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            if (conexion != null) {
                System.out.println("Conexion establecida muchas gracias");
            } else {
                System.out.println("Error al conectar");
            }
        } catch (SQLException sqle) {
            throw sqle;
        }
        return conexion;
    }
}
