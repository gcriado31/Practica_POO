package etsisi.juego;

import java.util.Scanner;

/**
 * Esta clase nos sirve para poder mostar por pantalla los mensajes y escanear lo que el usuario quiere.
 */
public class Viewer {
    // ATRIBUTOS
    private static Scanner input=new Scanner(System.in);
    // CONSTRUCTOR
    public Viewer(){ }
     // MÉTODOS

    /**
     * Muestra por pantalla el mensaje con un salto de línea.
     * @param msg El mensaje de tipo String que se desee.
     */
    public static void printString(String msg){
        System.out.println(msg);
    }

    /**
     * Muestra el mensaje y escanea la opción.
     * @param msg El mensaje de tipo String que se desee.
     * @return La elección del usuario.
     */
    public static char options(String msg){
       printString(msg);
       return input.next().toUpperCase().charAt(0);
    }

    /**
     * Muestra el mensaje y lee la línea siguiente.
     * @param msg El mensaje de tipo String que se desee.
     * @return Lo introducido por el usuario.
     */
    public static String readLine(String msg){
        printString(msg);
        return input.nextLine();
    }

    /**
     * Muestra el mensaje y lee el int siguiente.
     * @param msg La elección del usuario.
     * @return El int que introduzca el usuario.
     */
    public static int readInt(String msg){
        printString(msg);
        return input.nextInt();
    }

    /**
     * Introduce un salto de línea.
     */
    public static void introducirSaltoLinea(){
        System.out.println();
    }

    /**
     * Muestra el mensaje sin salto de línea posterior.
     * @param msg La elección del usuario.
     */
    public static void print(String msg){
        System.out.print(msg);
    }

    /**
     * Resetea el Scanner para evitar posibles bugs.
     */
    public static void reset(){
        input=new Scanner(System.in);
    }
}
