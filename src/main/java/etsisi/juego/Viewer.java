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
    public static void printString(String msg){
        System.out.println(msg);
    }
    public static char options(String msg){
       printString(msg);
       return input.next().toUpperCase().charAt(0);
    }
    public static String readLine(String msg){
        printString(msg);
        return input.nextLine();
    }
    public static int readInt(String msg){
        printString(msg);
        return input.nextInt();
    }
    public static void introducirSaltoLinea(){
        System.out.println();
    }

    public static void print(String msg){
        System.out.print(msg);
    }
}
