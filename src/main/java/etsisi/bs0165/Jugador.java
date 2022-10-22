package etsisi.bs0165;

/*
*  FALTA:
* */

public class Jugador {
    // ATRIBUTOS
    private String nombre;
    private Ficha ficha;
    private Tablero tablero;

    //CONSTRUCTORES
    public Jugador (String nombre, Ficha ficha){
        this.nombre=nombre;
        this.ficha=ficha;
    }

    public Jugador (String nombre, Ficha ficha, Tablero tablero){
        this.nombre=nombre;
        this.ficha=ficha;
        this.tablero=tablero;
    }

    //SETTERS Y GETTERS
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getNombre() {
        return nombre;
    }

    //MÉTODOS
    public void poner (){
        tablero.ponerFicha(ficha);
    }

}
