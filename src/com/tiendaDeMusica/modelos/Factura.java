package com.tiendaDeMusica.modelos;

import java.util.Arrays;

public class Factura {
    private int id;
    private String nombreCliente;
    private String Total;
    private Producto[] productos;

    public Factura() {
    }

    public Factura(int id, String nombreCliente, String Total, Producto[] productos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.Total = Total;
        this.productos = productos;
    }

    public Factura(String nombreCliente, String Total, Producto[] productos) {
        this.nombreCliente = nombreCliente;
        this.Total = Total;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getPrecioTotal() {
        return Total;
    }

    public void setPrecioTotal(String precioTotal) {
        this.Total = precioTotal;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", Total='" + Total + '\'' +
                ", productos=" + Arrays.toString(productos) +
                '}';
    }

}
