package etsisi.pilas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IteratorDLCircularStackTest {

    /**
     *  Hacemos un test a la creación del iterador.
     */
    @Test
    void creacionTest(){
        DLCircularStack<Integer> pila = inicializaPila();
        IteratorDLCircularStack<Integer> iterator=pila.generateIterator();
        assertNotNull(iterator);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest(){
        DLCircularStack<Integer> pila = inicializaPila();
        IteratorDLCircularStack<Integer> iterator=pila.generateIterator();
        assertDoesNotThrow(iterator::next);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest2(){
        DLCircularStack<Integer> pila = new DLCircularStack<>();
        IteratorDLCircularStack<Integer> iterator=pila.generateIterator();
        iterator.setStack(pila);
        assertThrows(StackEmptyException.class, iterator::next);
    }

    /**
     * Hacemos un test para comprobar el funcionamiento del método next.
     */
    @Test
    void nextTest3() throws NullInfoException, StackEmptyException {
        DLCircularStack<Integer> pila = inicializaPila();
        IteratorDLCircularStack<Integer> iterator=pila.generateIterator();
        for (int i = 0; i < 6; i++) {
            iterator.next();
        }
        assertEquals(5,iterator.getInfo());
    }


    /**
     * Metodo para inicializar la pila.
     * @return La pila ya inicializada
     */
    DLCircularStack<Integer> inicializaPila(){
        DLCircularStack<Integer> devuelto= new DLCircularStack<>();
        for (int i = 0; i < 12; i++) {
            devuelto.push(new Integer(i));
        }
        return devuelto;
    }
}
