package etsisi.pilas;

public interface Iterator<E> {
    /**
     * Este método sirve para conocer la información del nodo actual.
     * @return La información actual.
     * @throws NullInfoException En caso de que el nodo esté vacío (su información se null) saltará esta excepción.
     */
    E getInfo() throws NullInfoException;

    /**
     *
     * @throws StackEmptyException
     */
    void next() throws StackEmptyException;



}
