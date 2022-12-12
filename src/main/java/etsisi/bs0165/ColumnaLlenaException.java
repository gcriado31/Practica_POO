package etsisi.bs0165;

/**
 * Exception que saltará si la columna está llena.
 */
public class ColumnaLlenaException extends Exception{
    // ATRIBUTOS
    private static final String MENSAJE = "EXCEPTION: Esta columna está llena, por favor eliga otra";

    // CONSTRUCTOR
    public ColumnaLlenaException(){
        super(MENSAJE);
    }
}
