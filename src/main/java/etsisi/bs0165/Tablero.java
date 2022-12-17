package etsisi.bs0165;
import java.awt.*;
import java.util.Scanner;



/**
 *  Esta clase representará el tablero, pondrá las fichas, controlará si hay ganador o si se llena.
 */
public class Tablero {

    //ATRIBUTOS
    private final int numColumnas;
    private final int numFilas;
    private Casilla [][] casillas;
    private final String INTRODUCIR_COLUMNA="INTRODUZCA UNA COLUMNA: ";
    private final int INICIO_BUCLE=0;


    //CONSTRUCTOR
    public Tablero(int numFilas,int numColumnas){
        this.numFilas=numFilas;
        this.numColumnas=numColumnas;
        this.casillas=Inicios.iniciarCasillas(this.numFilas,this.numColumnas);
    }

    //GETTERS
    public int getNumColumnas() {
        return numColumnas;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public Ficha getFichaPos(int fila, int columna){
        return casillas[fila][columna].getFicha();
    }

    //MÉTODOS
    protected boolean isEmpty (int fila, int columna){
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
            }catch (ColumnaLlenaException | ColumnaIncorrectaException ex){
                System.out.println(ex.getMessage());
            }
        }
        return new Coordenadas(fila,columna);
    }

    /**
     * Este método sirve para que la ficha de la IA caiga por la columna seleccionada.
     * @param columna Número de columna por donde tiene que caer la ficha.
     * @return Devuelve la fila donde se queda la ficha.
     * @throws ColumnaLlenaException Si la columna está llena se lanzará esta excepcion.
     */
    public int caeFichaIA(int columna) throws ColumnaLlenaException {
        return this.caeFichaFila(columna);
    }

    public Coordenadas ponerFichaIA(int fila,int columna, Ficha ficha){
        casillas[fila][columna].setFicha(ficha);
        return new Coordenadas(fila,columna);
    }


    /**
     * Desliza la ficha por la columna.
     *
     * @param columna Se introduce la columna para que deslice por ella hasta la posición más baja vacía.
     * @return  Devuelve la posición fila mas baja que está vacía. En caso de que esté llena la columna devolverá -1.
     * @throws ColumnaLlenaException Si la columna seleccionada por el usuario está llena.
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
     * @throws ColumnaIncorrectaException Si el usuario intenta seleccionar una columna que no está entre el mínimo y el máximo
     */
    private int pedirColumna() throws ColumnaIncorrectaException {
        Scanner input = new Scanner(System.in);
        System.out.print(INTRODUCIR_COLUMNA);
        int pos=input.nextInt();
        if (!((pos<=numColumnas)&&(pos>=0))){
            throw new ColumnaIncorrectaException(0,(numColumnas-1));
        }
        return pos;
    }

    public static void main(String[] args) {
        Tablero t1= new Tablero(6,7);
        t1.dibujar();
        t1.ponerFicha(new Ficha('P', Color.BLUE));
        t1.dibujar();
    }

}
