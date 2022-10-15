package etsisi.bs0165;

import java.awt.*;

public class Ficha {
    //ATRIBUTOS
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
        return ""+contenido+"";
    }

    public boolean equals(Ficha ficha) {
        return (contenido== ficha.getContenido() && color.equals(ficha.getColor()));
    }
}
