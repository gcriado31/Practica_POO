package etsisi.pilas;

public class IteratorDLCircularStack<E> implements Iterator<E>{
    // ATRIBUTOS
    private DLNode<E> actualNode;
    private DLCircularStack<E> stack;

    // CONSTRUCTOR
    public IteratorDLCircularStack(DLNode<E> top){
        this.actualNode=top;
        this.stack=null;
    }
    public IteratorDLCircularStack(DLCircularStack<E> pila){
        this.stack=pila;
        this.actualNode=this.stack.getTop();
    }

    // MÉTODOS DE LA INTERFAZ
    /**
     * Este método sirve para conocer la información del nodo actual.
     *
     * @return La información actual.
     * @throws StackEmptyException En caso de que la pila esté vacía saltará esta excepción.
     */
    @Override
    public E getInfo() throws StackEmptyException {
        try {
            return this.actualNode.getInfo();
        }catch (NullInfoException ex){
            this.next();
            return this.getInfo(); // Volvemos a llamar al método con el nodo actual cambiado.
        }
    }

    /**
     * Este método sirve para pasar al siguiente nodo.
     *
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public void next() throws StackEmptyException {
        if (this.stack!=null){
            if(this.stack.isEmpty()){
                throw new StackEmptyException();
            }else{
                this.passNode();
            }
        }else{
            this.passNode();
        }

    }

    /**
     * Este método lo utilizaremos para volver al principio después de un bucle.
     * Solo se podrá utilizar tenemos una pila almacenada.
     */
    @Override
    public void backToFrist() {
        if(this.stack!=null){
            this.actualNode=this.stack.getTop().getNext();
        }
    }


    // MÉTODOS DE LA CLASE
    /**
     * Este método nos sirve para pasar del nodo actual al siguiente y manejar en caso de que se salga fuera de la pila.
     */
    private void passNode(){
        this.actualNode = this.actualNode.getNext();
    }

    /**
     * Este método sirve para cuando se cambie o modifique la pila se actualice en el iterador. El iterador volverá al principio.
     *
     * @param stack Se pasa la pila que queremos que se actualice.
     */
    public void setStack(DLCircularStack<E> stack) {
        this.stack=stack;
        this.actualNode=this.stack.getTop().getNext();
    }

}
