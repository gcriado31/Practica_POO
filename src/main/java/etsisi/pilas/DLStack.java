package etsisi.pilas;

public class DLStack <E> implements Stack<E> {




   //METODOS DE LA INTERFAZ
    /**
     * Comprueba que la pila esté vacía.
     *
     * @return Devolverá "true" si la pila está vacía.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Comprueba el tamaño de la pila.
     *
     * @return Devuelve el tamaño de la pila.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Mira el primer nodo y devuelve su información.
     *
     * @return Devuelve la información del primer nodo.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public E top() throws StackEmptyException {
        return null;
    }

    /**
     * Mete la información en un nodo y lo conecta al primer nodo
     *
     * @param info La información que queremos meter.
     */
    @Override
    public void push(E info) {

    }

    /**
     * Saca el primer nodo de la pila y reconecta.
     *
     * @return Devuelve la información almacenada.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public E pop() throws StackEmptyException {
        return null;
    }
}
