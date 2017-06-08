package com.tiendaDeMusica.misExcepciones;

public class Excepcion extends RuntimeException {
    public Excepcion() {
        this("[Ha ocurrido un error]");
    }

    public Excepcion(String message){
        super(message);
    }

}
