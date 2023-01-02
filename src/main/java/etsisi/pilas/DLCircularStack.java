package etsisi.pilas;

/**
 * Esta pila es una pila circular doblemente enlazada es decir el primer nodo está conectado al último
 * @param <E> Será el tipo de información que queramos guardar.
 */
public class DLCircularStack <E> implements Stack<E> {

    // ATRIBUTOS
    private int size;
    private DLNode <E> top;
    private DLNode <E> tail;

    // CONSTRUCTOR
    public DLCircularStack (){
        this.top=new DLNode<E>();
        this.tail=new DLNode<E>();
        this.top.setNext(tail);
        this.top.setPrev(tail); //PARA QUE ASÍ DESDE EL INICIO SEA CIRCULAR.
        this.tail.setPrev(top);
        this.tail.setNext(top);
        this.size=0;
    }

    // SETTERS Y GETTERS

    public int getSize() {
        return this.size;
    }

    public DLNode<E> getTop() {
        return this.top;
    }

    public DLNode<E> getTail() {
        return this.tail;
    }
    // MÉTODOS DE LA INTERFAZ.
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
            return this.top.getInfo();
        }
    }

    /**
     * Mete la información en un nodo y lo conecta (siendo el primero) de manera que siga siendo circular la pila, es decir conectado atrás con el último.
     *
     * @param info La información que queremos meter.
     */
    @Override
    public void push(E info) {
        DLNode<E> newNode;
        if(this.size==0){
            newNode=new DLNode<>(info,tail,tail);
            this.tail.setPrev(newNode);
            this.tail.setNext(newNode);
        }else{
            newNode=new DLNode<>(info,tail,top);
            this.tail.setNext(newNode);
            this.top.setPrev(newNode);
        }
        this.top=newNode;
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
        if(this.size==0){
           throw new StackEmptyException();
        }else{
          DLNode<E> popNode=this.top;
          this.top=popNode.getNext();
          // Desconesctamos el nodo de tail y de top para que no tenga ninguna relación con ellos.
          this.top.setPrev(tail);
          this.tail.setNext(tail);
          popNode.setNext(null);
          popNode.setPrev(null);
          // Decrementamos el tamaño.
          size--;
          return popNode.getInfo();
        }
    }
}
