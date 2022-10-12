package etsisi.bs0165;
/*
        FALTA:
            - PEDIRCOLUMNA
            - PEDIRFILA
            - HAYGANADOR
            - TABLERO LLENO
*/

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
    public boolean isEmpty (int fila, int columna){
        return casillas[fila][columna].isEmpty();
    }

    public void ponerFicha (int fila, int columna, Ficha ficha){
        if(isEmpty(fila,columna)){
            casillas[fila][columna].setFicha(ficha);
        }else{  //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "CasillaLlenaException"
            System.out.println("LA CASIILLA ESTÁ LLENA");
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




}
