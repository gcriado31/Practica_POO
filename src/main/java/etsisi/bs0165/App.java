package etsisi.bs0165;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Tablero prueba= new Tablero(4,4);
        prueba.dibujar();
        System.out.println(prueba.isEmpty(1,3));
    }
}
