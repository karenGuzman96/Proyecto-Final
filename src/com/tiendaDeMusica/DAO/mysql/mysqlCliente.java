package com.tiendaDeMusica.DAO.mysql;


import com.tiendaDeMusica.DAO.interfaces.ClientesDAO;
import com.tiendaDeMusica.misExcepciones.Excepcion;
import com.tiendaDeMusica.modelos.Clientes;

import java.sql.*;

public class mysqlCliente implements ClientesDAO {
    private final String INSERTAR = "INSERT INTO Cliente (Nombre, Apellidos, Cedula, Telefono, Direccion) VALUES (?, ?, ?, ?, ?)";
    private final String EDITAR = "UPDATE Cliente SET Nombre = ?, Apellidos = ?,Cedula = ?, Telefono = ?, Direccion = ? WHERE id_Cliente = ?";
    private final String LISTAR = "SELECT id_Cliente, Nombre, Apellidos, Cedula, Telefono, Direccion FROM Cliente";
    private final String ELIMINAR = "DELETE FROM Cliente WHERE id_Cliente = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Clientes o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getApellidos());
            sentencia.setString(3, o.getCedula());
            sentencia.setString(4, o.getTelefono());
            sentencia.setString(5, o.getDireccion());

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
    public void editar(Clientes o) {
        try {
            conexion = new mysqlConexion().conectar();
            sentencia = conexion.prepareStatement(EDITAR);
            sentencia.setString(1, o.getNombre());
            sentencia.setString(2, o.getApellidos());
            sentencia.setString(3, o.getCedula());
            sentencia.setString(4, o.getTelefono());
            sentencia.setString(5, o.getDireccion());
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
    public void eliminar(Clientes o) {
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

    public void cerrarConexion() {
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
