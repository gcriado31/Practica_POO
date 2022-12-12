package etsisi.bs0165;
import java.awt.*;
import java.util.Scanner;

/*
    TODO: COPIAR checkColumnas, checkFila, checkDiagonales (y métodos auxiliares)
 */

/**
 *  Esta clase representará el tablero, pondrá las fichas, controlará si hay ganador o si se llena.
 */
public class Tablero {

    //ATRIBUTOS
    private int numColumnas;
    private int numFilas;
    private Casilla [][] casillas;
    private final String INTRODUCIR_COLUMNA="INTRODUZCA UNA COLUMNA: ";

    private final String ERROR_COLUMNA_INCORRECTA="ERROR COLUMNA INCORRECTA\nVALORES VALIDOS [0-6]\n"+INTRODUCIR_COLUMNA;
    private final int INICIO_BUCLE=0;
    /**
     * Esta constante es el número de fichas que hay que tener en horizontal, vertical o diagonal para ganar
     */
    private final int NUM_FICHAS_GANADOR=4; // VA A CLASE VALIDACIONES

    //CONSTRUCTOR
    public Tablero(int numFilas,int numColumnas){
        this.numFilas=numFilas;
        this.numColumnas=numColumnas;
        this.iniciarCasillas();
    }

    /**
     * Este método inicia el array de casillas con las casillas en blanco.
     */
    private void iniciarCasillas(){
        this.casillas = new Casilla[this.numFilas][this.numColumnas];
        for (int i = INICIO_BUCLE; i < numFilas; i++) {
            for (int j = INICIO_BUCLE; j < numColumnas; j++) {
             this.casillas[i][j]=new Casilla(i,j);
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

    /**
     * Comprueba si el tablero está lleno.
     * @return Devolverá "true" si está lleno.
     */
    public boolean tableroLleno(){
        int contadorCasillasVacias=0;
        for (int i = INICIO_BUCLE; i < casillas.length; i++) {
            for (int j = INICIO_BUCLE; j < casillas[i].length; j++) {
                if (isEmpty(i,j)){
                    contadorCasillasVacias++;
                }
            }
        }
       return (contadorCasillasVacias==0);
    }

    /**
     * Este método se encarga de poner la ficha en el tablero.
     * @param ficha Se pasa una Ficha para saber qué ficha se ha de poner.
     * @return Devuelve las coordenadas de dónde se ha puesto la ficha.
     */
    public Coordenadas ponerFicha (Ficha ficha){
        int columna=0;
        int fila=0;
        boolean repetir=false;
        while (!repetir) {
            try {
                columna=pedirColumna();
                fila=this.caeFichaFila(columna);
                casillas[fila][columna].setFicha(ficha);
                repetir=true;
            }catch (ColumnaLlenaException ex){
                System.out.println(ex.getMessage());
            }
        }
        /*int fila=caeFichaFila(columna);
        while (fila==-1) { // Mientras se seleccione una columna llena se pedirá una nueva y se mostrará un mensaje de error (FUTURA ColumnaLlenaExcepcion)
            System.out.println(ERROR_COLUMNA_LLENA);
            columna=pedirColumna();
            fila=caeFichaFila(columna);
        }*/

        return new Coordenadas(fila,columna);
    }

    /**
     * Desliza la ficha por la columna.
     *
     * @param columna Se introduce la columna para que deslice por ella hasta la posición más baja vacía.
     * @return  Devuelve la posición fila mas baja que está vacía. En caso de que esté llena la columna devolverá -1.
     */
    private int caeFichaFila(int columna) throws ColumnaLlenaException {
        boolean casillaLlena=false;
        int indicadorFila=0;
        do{
            if(!isEmpty(indicadorFila,columna)){
                casillaLlena=true;
                indicadorFila--; //Restamos 1 ya que esa posición está ocupada
            }else{
                indicadorFila++;
            }
        }while(!casillaLlena && indicadorFila<numFilas);
        if(indicadorFila==numFilas){ //Esto significa que la columna está vacía y ha llegado hasta el final
            indicadorFila=numFilas-1;
        }
        if(indicadorFila==-1){
            throw new ColumnaLlenaException();
        }
        return indicadorFila;
    }

    public void dibujar(){
        System.out.println("-----------------------------------------------------");
        for (int i = INICIO_BUCLE; i < numFilas; i++) {
            for (int j = INICIO_BUCLE; j < numColumnas; j++) {
                System.out.print(casillas[i][j].dibujar()+"\t");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
    }

    /**
     * Pide la columna.
     * @return Devuelve el valor de la columna después de asegurase de que existe dicha columna.
     */
    private int pedirColumna(){
        Scanner input = new Scanner(System.in);
        System.out.print(INTRODUCIR_COLUMNA);
        int pos=input.nextInt();
        boolean posCorrecta = (pos<=numColumnas);
        while(!posCorrecta){   //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "ColumnaIncorrectaException"
            System.out.print(ERROR_COLUMNA_INCORRECTA);
            pos=input.nextInt();
            posCorrecta = pos<=numFilas;
            System.out.println();
        }
        System.out.println();
        return pos;
    }

    /**
     * Este método se encarga de encontrar (si hay) ganador.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @param posicion Se pasa la posición de la ficha puesta para partir de dicha posición al tirar diagonales.
     * @return Devuelve "true" si hay ganador.
     */
    public boolean hayGanador(Ficha ficha, Coordenadas posicion){
        boolean hayGanador=false;
        if(checkFilas(ficha)){
            hayGanador= true;
        } else if (checkColumnas(ficha)) {
            hayGanador= true;
        } else if (checkDiagonales(ficha,posicion)) {
            hayGanador= true;
        }
        return hayGanador;
    }

    /**
     * Busca por filas si hay 4 fichas en horizontal.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkFilas (Ficha ficha){
        int i = INICIO_BUCLE;
        boolean hayganador=false;
        while(i < casillas.length && !hayganador) {
            int contadorCasillas=0;
            for (int j = INICIO_BUCLE; j < casillas[i].length; j++) {
                if(!isEmpty(i,j)){
                    if (casillas[i][j].getFicha().equals(ficha)){
                        contadorCasillas++;
                    }
                }
            }
            if(contadorCasillas>=NUM_FICHAS_GANADOR){
                hayganador=true;
            }else {
                i++;
            }
        }
        return hayganador;
    }

    /**
     * Busca por filas si hay 4 fichas en vertical.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkColumnas (Ficha ficha){
        int i = 0;
        boolean hayganador=false;
        while(i < numColumnas && !hayganador) {
            int contadorCasillas=0;
            for (int j = INICIO_BUCLE; j < numFilas; j++) {
                if(!isEmpty(j,i)){
                    if (casillas[j][i].getFicha().equals(ficha)){
                        contadorCasillas++;
                    }
                }
            }
            if(contadorCasillas>=NUM_FICHAS_GANADOR){
                hayganador=true;
            }else {
                i++;
            }
        }
        return hayganador;
    }

    /**
     * Busca por filas si hay 4 fichas en diagonal.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @param posicion Se pasa la posición de la ficha puesta para partir de dicha posición al tirar diagonales.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkDiagonales(Ficha ficha, Coordenadas posicion){
        boolean hayGanador=false;
        int diagonalIzquierdaArriba=diagonalIzquierdaArriba(ficha,posicion);
        int diagonalIzquierdaAbajo=diagonalIzquierdaAbajo(ficha,posicion);
        int diagonalDerechaArriba=diagonalDerechaArriba(ficha,posicion);
        int diagonalDerechaAbajo=diagonalDerechaAbajo(ficha,posicion);
        // Sumamos las dos grandes diagonales por si sumadas dan 4. Se le resta 1 porque la ficha se cuenta dos veces
        int diagonalGrande1=(diagonalIzquierdaArriba+diagonalDerechaAbajo)-1;
        int diagonalGrande2=(diagonalDerechaArriba+diagonalIzquierdaAbajo)-1;

        if((diagonalIzquierdaArriba==4) || (diagonalDerechaArriba==4) || (diagonalDerechaAbajo==4) || (diagonalIzquierdaAbajo==4) || (diagonalGrande1==4) || (diagonalGrande2==4)){
            hayGanador=true;
        }
        return hayGanador;
    }

    private int diagonalIzquierdaArriba(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(casillas[fila][columna].getFicha().equals(ficha)){
                contadorFichas++;
            }
            if(contadorFichas==4){
                hayGanador=true;
            }else{
                fila--;
                columna--;
            }
        }
        return contadorFichas;
    }

    private int diagonalIzquierdaAbajo(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(casillas[fila][columna].getFicha().equals(ficha)){
                contadorFichas++;
            }
            if(contadorFichas==4){
                hayGanador=true;
            }else{
                fila++;
                columna--;
            }
        }
        return contadorFichas;
    }

    private int diagonalDerechaAbajo(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(casillas[fila][columna].getFicha().equals(ficha)){
                contadorFichas++;
            }
            if(contadorFichas==4){
                hayGanador=true;
            }else{
                fila++;
                columna++;
            }
        }
        return contadorFichas;
    }

    private int diagonalDerechaArriba(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(casillas[fila][columna].getFicha().equals(ficha)){
                contadorFichas++;
            }
            if(contadorFichas==4){
                hayGanador=true;
            }else{
                fila--;
                columna++;
            }
        }
        return contadorFichas;
    }

    private boolean isInArray(int fila, int columna){
        return (fila<numFilas && columna<numColumnas && fila>=0 && columna>=0);
    }



    public static void main(String[] args) {
        Tablero t1= new Tablero(6,7);
        t1.dibujar();
        t1.ponerFicha(new Ficha('P', Color.BLUE));
        t1.dibujar();
        System.out.println(t1.isInArray(1,-1));
    }

}
