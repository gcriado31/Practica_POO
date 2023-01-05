package etsisi.pilas;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DLCircularStackTest {
    /**
     * Test para saber si funciona bien el método top.
     */
    @Test
    void testTop(){
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        assertThrows(StackEmptyException.class, pila::top);
    }
    /**
     * Test para saber si se introduce bien la información.
     */
    @Test
    void testPush() throws StackEmptyException {
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        //pila.push(new Integer(8));
        assertEquals(5,pila.top());
    }
    /**
     * Test para saber si se referencia bien el nodo top.
     */
    @Test
    void testPush2() throws StackEmptyException {
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        assertNotNull(pila.getTop().getNext().getPrev());
    }

    /**
     * Test para saber si se referencia bien el nodo tail.
     */
    @Test
    void testPush3() throws StackEmptyException {
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        assertSame(pila.getTail(),pila.getTop().getNext());
    }

    /**
     * Test para saber si incrementa bien el tamaño
     */
    @Test
    void testPush4() throws StackEmptyException {
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        assertEquals(1,pila.size());
    }

    /**
     * Test para el metodo vacuum.
     */
    @Test
    void testVacuum(){
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        try {
            pila.vacuum();
            assertEquals(pila.getTail(),pila.getTop().getPrev());
        }catch (StackEmptyException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test para el metodo vacuum.
     */
    @Test
    void testVacuum2(){
        DLCircularStack<Integer> pila= new DLCircularStack<>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        try {
            pila.vacuum();
            assertEquals(pila.getTop(),pila.getTail().getNext());
        }catch (StackEmptyException e){
            System.out.println(e.getMessage());
        }
    }
}
