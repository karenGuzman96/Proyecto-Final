package com.tiendaDeMusica.modelos;


public class Producto {
    private int id;
    private String nombre;
    private String genero;
    private String precio;
    private String cantidad;

    public Producto() {
        int[] cantidad = new int[]{1, 2, 3, 4};
    }

    public Producto(int id, String nombre, String genero,String precio, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto(String nombre, String genero, String precio, String cantidad) {
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCantidad() {return cantidad;}

    public void setCantidad(String cantidad) {this.cantidad = cantidad;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", precio='" + precio + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
