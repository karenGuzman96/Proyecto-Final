package com.tiendaDeMusica.modelos;

public class Genero {
    private int id;
    private String vallenato;
    private String popular;
    private String salsa;

    public Genero (int id, String vallenato, String popular, String salsa){
        this.id = id;
        this.vallenato = vallenato;
        this.popular = popular;
        this.salsa = salsa;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getVallenato() {return vallenato;}

    public void setVallenato(String vallenato) {this.vallenato = vallenato;}

    public String getPopular() {return popular;}

    public void setPopular(String popular) {this.popular = popular;}

    public String getSalsa() {return salsa;}

    public void setSalsa(String salsa) {this.salsa = salsa;}

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", vallenato='" + vallenato + '\'' +
                ", Popular='" + popular + '\'' +
                ", Salsa='" + salsa + '\''+
                '}';
    }
}
