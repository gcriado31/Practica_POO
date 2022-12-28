package etsisi.pilas;

/**
 * Excepción que nos dice que la pila está vacía.
 */
public class StackEmptyException extends Exception {
    //ATRIBUTOS
    private static final String msg="La pila está vacia";

    //CONSTRUCTOR
    public StackEmptyException(){
        super(msg);
    }
}
