package etsisi.pilas;

/**
 *  Excepción que nos dice que la información del nodo al que accedemos es null.
 */
public class NullInfoException extends Exception{
    //ATRIBUTOS
    private static final String msg="La información a la que se intenta acceder es null";

    //CONSTRUCTOR
    public NullInfoException(){
        super(msg);
    }
}
