package com.tiendaDeMusica.DAO.mysql;


import com.tiendaDeMusica.DAO.interfaces.InventarioDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Inventario;

import java.sql.*;

public class mysqlInventario implements InventarioDAO {
    private final String INSERTAR = "INSERT INTO inventario (producto, cantidad) VALUES (?, ?)";
    private final String EDITAR = "UPDATE inventario SET producto = ?, cantidad = ? WHERE id_inventario = ?";
    private final String LISTAR = "SELECT id_inventario, producto, cantidad FROM inventario";
    private final String ELIMINAR = "DELETE FROM inventario WHERE id_inventario = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Inventario o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, String.valueOf(o.getProducto()));
            sentencia.setInt(2, o.getCantidad());

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
    public void editar(Inventario o) {
        try{
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, String.valueOf(o.getProducto()));
            sentencia.setInt(2, o.getCantidad());
            sentencia.setInt(3, o.getId());
            if(sentencia.executeUpdate() == 0){
                throw new Excepcion("[Error al Editar]");
            }
        }catch (SQLException sqle){
            throw new Excepcion(sqle.getMessage());
        }finally {
            cerrarConexion();
        }
    }

    @Override
    public void eliminar(Inventario o) {
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
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException sqle) {
        }
    }
}
