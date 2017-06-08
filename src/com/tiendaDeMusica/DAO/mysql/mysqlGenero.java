package com.tiendaDeMusica.DAO.mysql;

import com.tiendaDeMusica.DAO.interfaces.GeneroDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Genero;

import java.sql.*;

public class mysqlGenero implements GeneroDAO {
    private final String INSERTAR = "INSERT INTO Genero (vallenato, popular,salsa ) VALUES (?, ?, ?)";
    private final String EDITAR = "UPDATE Genero SET vallenato = ?, popular = ?, salsa = ?  WHERE id_genero = ?";
    private final String LISTAR = "SELECT id_Genero, vallenato, popular, salsa FROM genero";
    private final String ELIMINAR = "DELETE FROM Genero WHERE id_genero = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Genero o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, String.valueOf(o.getVallenato()));
            sentencia.setString(2, String.valueOf(o.getPopular()));
            sentencia.setString(3, String.valueOf(o.getSalsa()));
            sentencia.setString(4, String.valueOf(o.getId()));

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
    public void editar(Genero o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, String.valueOf(o.getVallenato()));
            sentencia.setString(2, String.valueOf(o.getPopular()));
            sentencia.setString(3, String.valueOf(o.getSalsa()));
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
    public void eliminar(Genero o) {
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
