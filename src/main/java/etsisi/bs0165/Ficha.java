package etsisi.bs0165;

import java.awt.*;

public class Ficha {
    private char contenido;
    private Color color;

    //CONSTRUCTORES
    public Ficha (char contenido, Color color){
        this.contenido=contenido;
        this.color=color;
    }
    public Ficha (){
        this.contenido=' ';
        this.color=Color.BLACK;
    }

    //GETTERS
    public char getContenido() {
        return contenido;
    }

    public Color getColor() {
        return color;
    }

    //MÉTODOS
    public String mostrar(){
        return " "+contenido+" ";
    }

}
