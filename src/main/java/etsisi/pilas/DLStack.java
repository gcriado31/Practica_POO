package etsisi.pilas;

/**
 *
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
                return this.top.getInfo();
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
        DLNode<E> newNode;
        if(size==0){
            newNode = new DLNode<E>(info, null, this.tail);
            this.tail.setPrev(newNode);
        }else {
            newNode = new DLNode<E>(info, null, this.top);
            this.top.setPrev(newNode);

        }
        this.top = newNode;
        this.size++;
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
            DLNode <E> popNode =this.top; // Al ser una pila cogemos siempre la primera posición.
            this.top= popNode.getNext(); // Cambiamos el nodo top al siguiente al top (el segundo).
            this.top.setPrev(null); // Quitamos la referencia al anterior nodo top.
            popNode.setNext(null);  // Quitamos la referencia al nuevo top del nodo que quitamos.
            this.size--;
            try {
                return popNode.getInfo();
            }catch (NullInfoException ex){
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }

    // MÉTODOS DE LA CLASE
    public IteratorDLStack<E> generateIterator(){
        return new IteratorDLStack<E>(this.top);
    }
}
