package etsisi.pilas;

/**
 * Pila de un cualquier objeto.
 * @param <E> Será el tipo de información que queramos guardar.
 */
public class DLStack <E> implements Stack<E> {

    // ATRIBUTOS
    private int size;
    private DLNode <E> top;
    private DLNode <E> tail;

    //CONSTRUCTOR
    public DLStack (){
        this.top=new DLNode<E>();
        this.tail=new DLNode<E>();
        this.top.setNext(tail);
        this.tail.setPrev(top);
        this.size=0;
    }

    // GETTERS

    public DLNode<E> getTop() {
        return top;
    }

    public DLNode<E> getTail() {
        return tail;
    }

    //METODOS DE LA INTERFAZ
    /**
     * Comprueba que la pila esté vacía.
     *
     * @return Devolverá "true" si la pila está vacía.
     */
    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    /**
     * Comprueba el tamaño de la pila.
     *
     * @return Devuelve el tamaño de la pila.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Mira el primer nodo y devuelve su información.
     *
     * @return Devuelve la información del primer nodo.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public E top() throws StackEmptyException {
        if(this.size==0){
            throw new StackEmptyException();
        }else{
            try {
                return this.top.getNext().getInfo();
            }catch (NullInfoException ex){
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }

    /**
     * Mete la información en un nodo y lo conecta al primer nodo
     *
     * @param info La información que queremos meter.
     */
    @Override
    public void push(E info) {
        // Creamos un nodo con conexiones a top y al siguiente a top.
        DLNode<E> newNode=new DLNode<>(info,top,top.getNext());
        // Conectamos el segundo nodo al nuevo
        top.getNext().setPrev(newNode);
        // Conectamos top al nuevo nodo
        top.setNext(newNode);
        size++;
    }

    /**
     * Saca el primer nodo de la pila y reconecta.
     *
     * @return Devuelve la información almacenada.
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public E pop() throws StackEmptyException {
        if (size==0){
            throw new StackEmptyException();
        }else{
            DLNode <E> popNode =this.top.getNext(); // Al ser una pila cogemos siempre la primera posición.
            // El segundo nodo pasa a conectarse con top
            popNode.getNext().setPrev(top);
            top.setNext(popNode.getNext());
            // Quitamos las conexiones del nodo que vamos a expulsar
            popNode.setNext(null);
            popNode.setPrev(null);
            this.size--;
            try {
                return popNode.getInfo();
            }catch (NullInfoException ex){
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }

    @Override
    public void vacuum() {
        if(!this.isEmpty()) {
            if(this.size>1) {
                this.top.getNext().setPrev(null);  // Quitamos la referencia del segundo nodo al top.
                this.tail.getPrev().setNext(null); // Quitamos la referencia del penúltimo nodo al tail.
            }
            // Quitamos la información que haya en top.
            this.top.setInfo(null);
            // Volvemos a conectar top con tail.
            this.top.setNext(tail);
            // Volvemos a conectar tail con top.
            this.tail.setPrev(top);
            this.size=0;

        }else{
            try {
                throw new StackEmptyException();
            } catch (StackEmptyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // MÉTODOS DE LA CLASE
    public IteratorDLStack<E> generateIterator(){
        return new IteratorDLStack<E>(this.top);
    }
}
