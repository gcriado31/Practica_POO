package etsisi.bs0165;
/*
        FALTA:

*/

import java.util.Scanner;

public class Tablero {
    //ATRIBUTOS
    private int numColumnas;
    private int numFilas;
    private Casilla [][] casillas;

    //CONSTRUCTOR
    public Tablero(int numFilas,int numColumnas){
        this.numFilas=numFilas;
        this.numColumnas=numColumnas;
        iniciarCasillas();
    }


    private void iniciarCasillas(){     // Este método inicia el array de casillas con la ficha en blanco
        this.casillas = new Casilla[numFilas][numColumnas];
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
             casillas[i][j]=new Casilla(i,j);
            }
        }
    }

    //GETTERS
    public int getNumColumnas() {
        return numColumnas;
    }

    public int getNumFilas() {
        return numFilas;
    }

    //MÉTODOS
    private boolean isEmpty (int fila, int columna){
        return casillas[fila][columna].isEmpty();
    }

    public boolean tableroLleno(){
        int contadorCasillasVacias=0;
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (isEmpty(i,j)){
                    contadorCasillasVacias++;
                }
            }
        }
       return (contadorCasillasVacias==0);
    }

    public void ponerFicha (Ficha ficha){
        boolean casillaCorrecta=false;
        while(casillaCorrecta==false) {
            int fila = pedirFila();
            int columna = pedirColumna();
            if (isEmpty(fila, columna)) {
                casillas[fila][columna].setFicha(ficha);
                casillaCorrecta=true;
            } else {  //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "CasillaLlenaException"
                System.out.println("LA CASILLA ESTÁ LLENA\nELIJA OTRA");
            }
        }
    }

    public void dibujar(){
        System.out.println("-----------------------------");
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                System.out.print(casillas[i][j].dibujar()+" \t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

    private int pedirFila(){
        Scanner input = new Scanner(System.in);
        System.out.print("INTRODUZCA UNA FILA: ");
        int pos=input.nextInt();
        boolean posCorrecta = pos<=numFilas;
        while(!posCorrecta){   //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "FilaIncorrectaException"
            System.out.print("ERROR FILA INCORRECTA\nINTRODUZCA UNA FILA: ");
            pos=input.nextInt();
            posCorrecta = pos<=numFilas;
            System.out.println();
        }
        System.out.println();
        return pos;
    }

    private int pedirColumna(){
        Scanner input = new Scanner(System.in);
        System.out.print("INTRODUZCA UNA COLUMNA: ");
        int pos=input.nextInt();
        boolean posCorrecta = pos<=numFilas;
        while(!posCorrecta){   //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "ColumnaIncorrectaException"
            System.out.print("ERROR COLUMNA INCORRECTA\nINTRODUZCA UNA COLUMNA: ");
            pos=input.nextInt();
            posCorrecta = pos<=numFilas;
            System.out.println();
        }
        System.out.println();
        return pos;
    }

    public boolean hayGanador(Ficha ficha){
        boolean hayGanador=false;
        if(checkFilas(ficha)||checkColumnas(ficha)){
            return true;
        }
        return hayGanador;
    }

    private boolean checkFilas (Ficha ficha){
        int i = 0;
        boolean hayganador=false;
        while(i < casillas.length && hayganador==false) {
            int contadorCasillas=0;
            for (int j = 0; j < casillas[i].length; j++) {
                if(!isEmpty(i,j)){
                    if (casillas[i][j].getFicha().equals(ficha)){
                        contadorCasillas++;
                    }
                }
            }
            if(contadorCasillas>=4){
                hayganador=true;
            }else {
                i++;
            }
        }
        return hayganador;
    }

    private boolean checkColumnas (Ficha ficha){
        int i = 0;
        boolean hayganador=false;
        while(i < casillas.length && hayganador==false) {
            int contadorCasillas=0;
            for (int j = 0; j < casillas[i].length; j++) {
                if(!isEmpty(i,j)){
                    if (casillas[j][i].getFicha().equals(ficha)){
                        contadorCasillas++;
                    }
                }
            }
            if(contadorCasillas>=4){
                hayganador=true;
            }else {
                i++;
            }
        }
        return hayganador;
    }

    public boolean checkDiagonales(){
        // FALTA POR HACER
        return false;
    }

    public static void main(String[] args) {
        Tablero t1= new Tablero(6,7);
        t1.checkDiagonales();
    }

}
