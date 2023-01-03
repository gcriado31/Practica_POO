package etsisi.pilas;

public class IteratorOutOfStackException extends Exception {
    // ATRIBUTOS
    private static final String msg="Se ha llegado al final de la pila.";

    // CONSTRUCTOR
    public IteratorOutOfStackException(){
        super(msg);
    }
}
