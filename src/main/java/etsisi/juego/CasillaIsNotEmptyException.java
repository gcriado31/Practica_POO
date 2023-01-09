package etsisi.juego;

/**
 * Excepción que nos indica que la casilla está ocupada cuando queramos rehacer movimientos.
 */
public class CasillaIsNotEmptyException extends Exception{
    // ATRIBUTOS
    private static final String msg="La casilla está ocupada, no se puede rehacer movimiento";

    // CONSTRUCTOR
    public CasillaIsNotEmptyException(){
        super(msg);
    }
}
