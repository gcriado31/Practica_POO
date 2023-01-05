package etsisi.pilas;

public class IteratorDLStack <E> implements Iterator<E>{

    // ATRIBUTOS
    private DLNode<E> actualNode;
    private DLStack<E> stack;

    // CONSTRUCTORES
    public IteratorDLStack(DLStack<E> pila){
        this.stack=pila;
        this.actualNode=this.stack.getTop().getNext(); //Para situarnos en el primer nodo con información
    }

    public IteratorDLStack(DLNode<E> top){
        this.actualNode=top;
        this.stack=null;
    }


    // MÉTODOS DE LA INTERFAZ
    /**
     * Este método sirve para conocer la información del nodo actual.
     *
     * @return La información actual.
     * @throws NullInfoException En caso de que el nodo esté vacío (su información se null) saltará esta excepción.
     */
    @Override
    public E getInfo() throws NullInfoException {
        return this.actualNode.getInfo();
    }

    /**
     * Este método sirve para pasar al siguiente nodo.
     *
     * @throws StackEmptyException Si la cola está vacía salta la excepción.
     */
    @Override
    public void next() throws StackEmptyException {
        if (this.stack!=null) { // Solo podemos saber si está vacía la pila si tenemos una.
            if(this.stack.isEmpty()) {
                throw new StackEmptyException();
            }else{
                this.passNode();
            }
        } else {
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
            this.actualNode=this.stack.getTop().getNext(); //Para situarnos en el primer nodo con información
        }
    }

    // MÉTODOS DE LA CLASE
    /**
     * Este método sirve para cuando se cambie o modifique la pila se actualice en el iterador. El iterador volverá al principio.
     *
     * @param stack Se pasa la pila que queremos que se actualice.
     */
    public void setStack(DLStack<E> stack) {
        this.stack=stack;
        this.actualNode=this.stack.getTop().getNext(); //Para situarnos en el primer nodo con información
    }

    /**
     * Este método lo utilizamos para saber si el iterador estará fuera al pasar al siguiente nodo.
     * @throws IteratorOutOfStackException Si estamos en el último nodo y queremos pasar al siguiente saldrá esta excepción.
     */
    private void isInStack() throws IteratorOutOfStackException {
        try {
           this.actualNode.getNext().getInfo();
        } catch (NullInfoException e) {
            throw new IteratorOutOfStackException();
        }
    }

    /**
     * Este método nos sirve para pasar del nodo actual al siguiente y manejar en caso de que se salga fuera de la pila.
     */
    private void passNode(){
        try {
            this.isInStack();
            this.actualNode = this.actualNode.getNext();
        } catch(IteratorOutOfStackException e){
            System.out.println(e.getMessage());
        }
    }


}
