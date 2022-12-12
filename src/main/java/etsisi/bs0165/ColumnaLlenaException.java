package etsisi.bs0165;

public class ColumnaLlenaException extends Exception{
    // ATRIBUTOS
    private static final String MENSAJE = "EXCEPTION: Esta columna está llena, por favor eliga otra";

    // CONSTRUCTOR
    public ColumnaLlenaException(){
        super(MENSAJE);
    }
}
