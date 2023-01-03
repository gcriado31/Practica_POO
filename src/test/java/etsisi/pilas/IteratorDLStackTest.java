package etsisi.pilas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IteratorDLStackTest {

    /**
     * Hacemos un test a la creación del iterador.
     */
    @Test
    void creacionTest(){
        DLStack<Integer> pila = inicializaPila();
        IteratorDLStack<Integer> iterator=pila.generateIterator();
        assertNotNull(iterator);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest(){
        DLStack<Integer> pila = inicializaPila();
        IteratorDLStack<Integer> iterator=pila.generateIterator();
        assertDoesNotThrow(iterator::next);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest2(){
        DLStack<Integer> pila = new DLStack<>();
        IteratorDLStack<Integer> iterator=pila.generateIterator();
        iterator.setStack(pila);
        assertThrows(StackEmptyException.class, iterator::next);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest3() throws NullInfoException, StackEmptyException {
        DLStack<Integer> pila = inicializaPila();
        IteratorDLStack<Integer> iterator=pila.generateIterator();
        for (int i = 0; i < 6; i++) {
            iterator.next();
        }
        assertEquals(5,iterator.getInfo());
    }


    /**
     * Metodo para inicializar la pila.
     * @return La pila ya inicializada
     */
    DLStack<Integer> inicializaPila(){
        DLStack<Integer> devuelto= new DLStack<>();
        for (int i = 0; i < 12; i++) {
            devuelto.push(new Integer(i));
        }
        return devuelto;
    }

}
