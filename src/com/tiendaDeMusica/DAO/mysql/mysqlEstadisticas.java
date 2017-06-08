package com.tiendaDeMusica.DAO.mysql;

import com.tiendaDeMusica.DAO.interfaces.EstadisticasDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Estadistica;

import java.sql.*;

public class mysqlEstadisticas implements EstadisticasDAO {
    private final String INSERTAR = "INSERT INTO estadistica(id, producto, precio) VALUES (?, ?, ?)";
    private final String EDITAR = "UPDATE estadistica SET id = ?, producto = ?, precio = ? WHERE id_estadistica = ?";
    private final String LISTAR = "SELECT id_estadistica, id, producto, precio FROM estadistica";
    private final String ELIMINAR = "DELETE FROM estadistica WHERE id_estadistica = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Estadistica o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, String.valueOf(o.getId()));
            sentencia.setString(2, o.getPrecio());
            sentencia.setString(2, o.getProducto());
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
    public void editar(Estadistica o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, String.valueOf(o.getId()));
            sentencia.setString(2, o.getProducto());
            sentencia.setString(3, String.valueOf(o.getPrecio()));
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
    public void eliminar(Estadistica o) {
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

