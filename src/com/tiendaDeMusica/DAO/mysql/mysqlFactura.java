package com.tiendaDeMusica.DAO.mysql;


import com.tiendaDeMusica.DAO.interfaces.FacturaDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Factura;

import java.sql.*;

public class mysqlFactura implements FacturaDAO{
    private final String INSERTAR = "INSERT INTO factura(nombreCliente, precioTotal, productos) VALUES (?, ?, ?)";
    private final String EDITAR = "UPDATE factura SET nombreCliente = ?, precioTotal = ?, productos = ? WHERE id_factura = ?";
    private final String LISTAR = "SELECT id_factura, nombreCliente, precioTotal, productos FROM factura";
    private final String ELIMINAR = "DELETE FROM factura WHERE id_factura = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Factura o) {
        try{
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, o.getNombreCliente());
            sentencia.setString(2, o.getPrecioTotal());
            sentencia.setString(3, String.valueOf(o.getProductos()));

            if(sentencia.executeUpdate() == 0){
                throw new Excepcion("[Error al Insertar]");
            }resultado = sentencia.getGeneratedKeys();
            if(resultado.next()){
                o.setId(resultado.getInt(1));
            }
        }catch (SQLException sqle){
            throw new Excepcion(sqle.getMessage());
        }finally {
            cerrarConexion();
        }
    }

    @Override
    public void editar(Factura o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, o.getNombreCliente());
            sentencia.setString(2, o.getPrecioTotal());
            sentencia.setString(3, String.valueOf(o.getProductos()));
            sentencia.setInt(4, o.getId());

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
    public void eliminar(Factura o) {
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
