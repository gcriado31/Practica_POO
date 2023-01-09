package etsisi.juego;
import java.awt.*;


/**
 * La ficha que representa a cada jugador en el tablero
 */
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

    //MÉTODO
    public String mostrar(){
        return " "+contenido+" ";
    }

    /** Este método comprueba si la ficha es igual a otra.
     * @return Devuelve true or false dependiendo de si el contenido y el color es el mismo
     */
    public boolean equals(Ficha ficha) {
        return (contenido== ficha.getContenido() && color.equals(ficha.getColor()));
    }
}
