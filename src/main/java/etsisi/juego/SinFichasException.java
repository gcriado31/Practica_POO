package etsisi.juego;

/**
 * Excepcion que saltará en caso de que los jugadores se queden sin fichas.
 */
public class SinFichasException extends Exception{
    //ATRIBUTOS
    private static final String MENSAJE="EXCEPTION: ¡¡¡Te quedaste sin fichas!!!!\n\tMala suerte para: ";

    //CONSTRUCTOR
    public SinFichasException(String nombreJugador){
        super(MENSAJE+nombreJugador);
    }
}
