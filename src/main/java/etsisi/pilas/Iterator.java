package etsisi.pilas;

/**
 *  La interfaz de dieseño de los iteradores de las pilas.
 * @param <E> Será el tipo de información que queramos guardar.
 */
public interface Iterator<E> {
    /**
     * Este método sirve para conocer la información del nodo actual.
     * @return La información actual.
     * @throws NullInfoException En caso de que el nodo esté vacío (su información se null) saltará esta excepción.
     */
    E getInfo() throws NullInfoException, StackEmptyException;

    /**
     * Este método sirve para pasar al siguiente nodo.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    void next() throws StackEmptyException;

    /**
     * Este método lo utilizaremos para volver al principio después de un bucle.
     * Solo se podrá utilizar tenemos una pila almacenada.
     */
    void backToFrist();




}
