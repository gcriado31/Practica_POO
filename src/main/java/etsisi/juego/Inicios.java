package etsisi.juego;

import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase nos sirve para inicializar arrays o cualquier cosa a un valor determinado.
 */
public class Inicios {
    private static final int INICIO_BUCLE=0;

    /**
     * Este método inicia el array de casillas con las casillas en blanco.
     */
    public static Casilla[][] iniciarCasillas(int numFilas, int numColumnas){
        Casilla[][] devuelto = new Casilla[numFilas][numColumnas];
        for (int i = INICIO_BUCLE; i < numFilas; i++) {
            for (int j = INICIO_BUCLE; j < numColumnas; j++) {
                devuelto[i][j]=new Casilla(i,j);
            }
        }
        return devuelto;
    }

    /**
     * Inicializa un ArrayList.
     * @param size tamaño del ArrayList a iniciar.
     * @return ArrayList inicializado.
     */
    public static ArrayList<Integer> inicializarArrayList(int size){
        ArrayList<Integer> devuelto=new ArrayList<Integer>(size);
        for (int i=0;i<size;i++) {
            devuelto.add(i);
        }
        return devuelto;
    }


    /**
     * Inicializa un Tablero.
     * @param numFilas Nº de filas que tiene que tener.
     * @param numColumnas Nº de columnas que tiene que tener.
     * @return Tablero en blanco.
     */
    public static Tablero nuevoTablero(int numFilas, int numColumnas){
        return new Tablero(numFilas, numColumnas);
    }


    public static void main(String[] args) {
        ArrayList<Integer> prueba= inicializarArrayList(20);
        int columna=0;
        int i=0;
        Random random=new Random();
        for (Integer integer : prueba) {
            System.out.println("Valores "+i);
            System.out.println("\tArray List: "+integer);
            columna = random.nextInt(7 - 1);
            System.out.println("\tRandom: "+columna);
            i++;
        }
    }
}
