package etsisi.pilas;

/**
 * El nodo doblemente enlazado que compondrá nuestra pila.
 * @param <E> Será el tipo de información que queramos guardar.
 */
public class DLNode <E>{
    //ATRIBUTOS
    private E info;
    private DLNode <E> prev;
    private DLNode <E> next;

    //CONSTRUCTOR
    public DLNode(E info, DLNode <E> prev,DLNode <E> next){
        this.setInfo(info);
        this.setPrev(prev);
        this.setNext(next);
    }

    public DLNode(){
        this(null,null,null);
    }
    public DLNode(E info){
       this(info,null,null);
    }
    //SETTERS Y GETTERS

    /**
     * Nos devuelve la información.
     * @return La información almacenada en el nodo
     * @throws NullInfoException Si la información es null saltará esta excepción.
     */
    public E getInfo() throws NullInfoException {
        if(this.info==null){
            throw new NullInfoException();
        }else {
            return this.info;
        }
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public DLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DLNode<E> prev) {
        this.prev = prev;
    }

    public DLNode<E> getNext() {
        return next;
    }

    public void setNext(DLNode<E> next) {
        this.next = next;
    }
}
