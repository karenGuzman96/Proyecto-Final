package com.tiendaDeMusica.DAO.mysql;


import com.tiendaDeMusica.DAO.interfaces.ProductosDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Producto;

import java.sql.*;

public class mysqlProducto implements ProductosDAO {
    private final String INSERTAR = "INSERT INTO producto (nombre, genero, precio, cantidad) VALUE(?, ?, ?, ?)";
    private final String EDITAR = "UPDATE producto SET nombre = ?, genero = ?, precio = ?, cantidad = ? WHERE id_producto = ?";
    private final String LISTAR = "SELECT id_producto, nombre, genero, precio, cantidad FROM producto";
    private final String ELIMINAR = "DELETE FROM producto WHERE id_producto = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;


    @Override
    public void insertar(Producto o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getGenero());
            sentencia.setString(3, o.getPrecio());
            sentencia.setString(5, o.getCantidad());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("[Error al Insertar]");
            }
            resultado = sentencia.getGeneratedKeys();
            if (resultado.next()) {
                o.setId(resultado.getInt(1));
            }

        } catch (SQLException sqle) {
            throw new Excepcion(sqle.getMessage());
        } finally {
            cerrarConexion();
        }


    }

    @Override
    public void editar(Producto o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getGenero());
            sentencia.setString(3, String.valueOf(o.getPrecio()));
            sentencia.setString(5, o.getCantidad());
            sentencia.setInt(7, o.getId());

            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("[Error al Editar]");
            }

        } catch (SQLException sqle) {
            throw new Excepcion(sqle.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public void eliminar(Producto o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setInt(1, o.getId());
            if (sentencia.executeUpdate() == 0) {
                throw new Excepcion("[Error al Eliminar]");
            }
        } catch (SQLException sqle) {
            throw new Excepcion(sqle.getMessage());
        } finally {
            cerrarConexion();
        }
    }


    public void cerrarConexion(){
        try{
            if(resultado != null){
                resultado.close();
            }
            if(sentencia != null){
                sentencia.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }catch (SQLException sqle){}
    }

}
