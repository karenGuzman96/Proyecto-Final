package com.tiendaDeMusica.DAO.interfaces;


public interface DAO<O, k> {
    public void insertar(O o);
    public void editar(O o);
    public void eliminar(O o);
}
