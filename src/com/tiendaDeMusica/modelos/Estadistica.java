package com.tiendaDeMusica.modelos;

public class Estadistica {
    private int id;
    private String producto;
    private String precio;

    public Estadistica(){

    }

    public Estadistica(int id, String producto, String precio) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
    }

    public Estadistica(String producto, String precio) {
        this.producto = producto;
        this.precio = precio;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getProducto() {return producto;}

    public void setProducto(String producto) {this.producto = producto;}

    public String getPrecio() {return precio;}

    public void setPrecio(String precio) {this.precio = precio;}

    @Override
    public String toString() {
        return "Estadisticas{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}