package etsisi.pilas;

/**
 * La interfaz de diseño de nuestras pilas.
 * @param <E> Será el tipo de información que queramos guardar.
 */
public interface Stack<E> {
    /**
     * Comprueba que la pila esté vacía.
     * @return Devolverá "true" si la pila está vacía.
     */
    boolean isEmpty();

    /**
     * Comprueba el tamaño de la pila.
     * @return Devuelve el tamaño de la pila.
     */
    int size();

    /**
     * Mira el primer nodo y devuelve su información.
     * @return Devuelve la información del primer nodo.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    E top() throws StackEmptyException;

    /**
     * Mete la información en un nodo y lo conecta al primer nodo
     * @param info La información que queremos meter.
     */
    void push(E info);

    /**
     * Saca el primer nodo de la pila y reconecta.
     * @return Devuelve la información almacenada.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    E pop() throws StackEmptyException;
}
