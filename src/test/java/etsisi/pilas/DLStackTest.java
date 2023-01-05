package etsisi.pilas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class DLStackTest{

    /**
     * Test para saber si funciona bien el método top.
     */
    @Test
     void testTop(){
        DLStack<Integer> pila= new DLStack<Integer>();
        assertThrows(StackEmptyException.class, pila::top);
    }

    /**
     * Test para saber si se introduce bien la información.
     */
    @Test
    void testPush() throws StackEmptyException {
        DLStack<Integer> pila= new DLStack<Integer>();
        pila.push(new Integer(5));
        //pila.push(new Integer(8));
        assertEquals(5,pila.top());
    }

    /**
     * Test para saber si se referencia bien el nodo top.
     */
    @Test
    void testPush2() throws StackEmptyException {
        DLStack<Integer> pila= new DLStack<Integer>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        assertNotNull(pila.getTop().getNext().getPrev());
    }

    /**
     * Test para saber si se referencia bien el nodo tail.
     */
    @Test
    void testPush3() throws StackEmptyException {
        DLStack<Integer> pila= new DLStack<Integer>();
        pila.push(new Integer(5));
        assertSame(pila.getTail(),pila.getTop().getNext().getNext());
    }


    /**
     * Test para saber si funciona bien el método isEmpty.
     */
    @Test
    void testIsEmpty(){
        DLStack<Integer> pila= new DLStack<Integer>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        assertFalse(pila.isEmpty());
    }

    /**
     * Test para saber si funciona bien el método pop.
     */
    @Test
    void testPop() throws StackEmptyException {
        DLStack<Integer> pila= new DLStack<Integer>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        pila.push(new Integer(9));
        Integer prueba=pila.pop();
        try {
            assertNotEquals(pila.getTop().getNext().getInfo(),prueba);
        } catch (NullInfoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test para el metodo vacuum.
     */
    @Test
    void testVacuum(){
        DLStack<Integer> pila= new DLStack<>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        pila.vacuum();
        assertEquals(pila.getTail(),pila.getTop().getNext());

    }

    /**
     * Test para el metodo vacuum.
     */
    @Test
    void testVacuum2(){
        DLStack<Integer> pila= new DLStack<>();
        pila.push(new Integer(5));
        pila.push(new Integer(8));
        pila.vacuum();
        assertEquals(pila.getTop(),pila.getTail().getPrev());

    }

}
