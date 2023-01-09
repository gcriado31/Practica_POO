package etsisi.juego;

import java.awt.*;
import java.util.InputMismatchException;




/**
 *  Esta clase representará el tablero, pondrá las fichas, controlará si hay ganador o si se llena.
 */
public class Tablero {

    //ATRIBUTOS
    protected final int numColumnas;
    protected final int numFilas;
    protected Casilla [][] casillas;
    private final String INTRODUCIR_COLUMNA="INTRODUZCA UNA COLUMNA: ";
    private final String FORMATO_NUMERO="Formato de número incorrecto [0-5]";
    private final String LINEAS="------------------------------------------------------";
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
        Coordenadas posicion=new Coordenadas(fila,columna);
        while (!repetir) {
            try {
                columna=pedirColumna();
                fila=this.caeFichaFila(columna);
                casillas[fila][columna].setFicha(ficha);
                posicion.setFila(fila);
                posicion.setColumna(columna);
                repetir=true;
            }catch (ColumnaLlenaException | ColumnaIncorrectaException ex){
                Viewer.printString(ex.getMessage());
            }catch (InputMismatchException ex){
                Viewer.printString(FORMATO_NUMERO);
            }
        }
        return posicion;
    }
    /**
     * Desliza la ficha por la columna.
     *
     * @param columna Se introduce la columna para que deslice por ella hasta la posición más baja vacía.
     * @return  Devuelve la posición fila mas baja que está vacía. En caso de que esté llena la columna devolverá -1.
     * @throws ColumnaLlenaException Si la columna seleccionada por el usuario está llena.
     */
    protected int caeFichaFila(int columna) throws ColumnaLlenaException {
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

    /**
     * Este método sirve para que la ficha de la IA caiga por la columna seleccionada.
     * @param columna Número de columna por donde tiene que caer la ficha.
     * @return Devuelve la fila donde se queda la ficha.
     * @throws ColumnaLlenaException Si la columna está llena se lanzará esta excepcion.
     */
    public int caeFichaIA(int columna) throws ColumnaLlenaException {
        return this.caeFichaFila(columna);
    }

    /**
     * Método para que la IA ponga su ficha.
     * @param fila Fila donde poner la ficha.
     * @param columna Columna donde poner la ficha.
     * @param ficha Ficha a poner
     * @return Coordenadas de donde se pone.
     */
    public Coordenadas ponerFichaIA(int fila,int columna, Ficha ficha){
        this.casillas[fila][columna].setFicha(ficha);
        return new Coordenadas(fila,columna);
    }

    /**
     * Dibuja el tablero.
     */
    public void dibujar(){
        Viewer.printString(LINEAS);
        for (int i = INICIO_BUCLE; i < numFilas; i++) {
            for (int j = INICIO_BUCLE; j < numColumnas; j++) {
                Viewer.print((casillas[i][j].dibujar()+"\t"));
            }
            Viewer.introducirSaltoLinea();
        }
        Viewer.printString(LINEAS);
    }

    /**
     * Pide la columna.
     * @return Devuelve el valor de la columna después de asegurase de que existe dicha columna.
     * @throws ColumnaIncorrectaException Si el usuario intenta seleccionar una columna que no está entre el mínimo y el máximo
     */
    private int pedirColumna() throws ColumnaIncorrectaException {
        int pos=Viewer.readInt(INTRODUCIR_COLUMNA);
        if (!((pos<=numColumnas)&&(pos>=0))){
            throw new ColumnaIncorrectaException(0,(numColumnas-1));
        }
        return pos;
    }

    /**
     * Este método quita la ficha de las coordenadas y baja las que estén encima.
     * @param coordenadas Coordenadas de la ficha a quitar.
     */
   public void quitarFicha(Coordenadas coordenadas){
        if(!this.isEmpty(coordenadas.getFila(), coordenadas.getColumna())){
            System.out.println("Quitando ficha con coordenadas x="+coordenadas.getFila()+" y="+coordenadas.getColumna());
            this.gravedad(coordenadas);
        }
    }

    /**
     * Aplica la gravedad a la ficha.
     * @param coordenadas Coordenadas de la ficha a quitar.
     */
    private void gravedad(Coordenadas coordenadas){
        if((coordenadas.getFila()-1)>0) { // Si hay casillas arriba
            if (this.isEmpty((coordenadas.getFila() - 1), (coordenadas.getColumna()))) { // Si la casilla de arriba está vacía se pondrá la casilla a vacío.
                this.casillas[coordenadas.getFila()][coordenadas.getColumna()].setFicha(new Ficha());
            } else {
               this.cambiarFichaAbajo(coordenadas);
            }
        }else{
            this.casillas[coordenadas.getFila()][coordenadas.getColumna()].setFicha(new Ficha());
        }
    }

    /**
     * Baja las fichas.
     * @param coordenadas Coordenadas de la ficha a bajar.
     */
    private void cambiarFichaAbajo(Coordenadas coordenadas){
        int fila=(coordenadas.getFila() - 1);
        int columna=coordenadas.getColumna();
        boolean repetir=true;
        Casilla casillaActual = this.casillas[fila][columna];
        do{
            if(!casillaActual.isEmpty()){
                casillas[fila+1][columna].setFicha(casillaActual.getFicha()); // Bajamos la ficha.
                casillas[fila][columna].setFicha(new Ficha()); //Dejamos la ficha vacía en el array.
                fila--;
                try {
                    casillaActual=casillas[fila][columna];
                }catch (ArrayIndexOutOfBoundsException ex){
                    repetir=false;
                }
            }else{
                repetir=false;
            }
        }while (repetir);
    }

    /**
     * Pone la ficha en las coordenadas y sube las que estén en esas coordenadas.
     * @param movimiento Movimiento a restituir.
     */
    public void cambiarFichasArriba(Movimiento movimiento){
        int fila=movimiento.getCoordenadasFichaIntroducida().getFila();
        int columna=movimiento.getCoordenadasFichaIntroducida().getColumna();
        if(this.casillas[fila][columna].isEmpty()){ // Si está vacía simplemente tenemos que introducir la ficha
            this.casillas[fila][columna].setFicha(movimiento.getFichaJugador());
        }else{ // Si no está vacía tenemos que subir todas las fichas.
            boolean repetir=true;
            do{
                try {
                    repetir=this.cambiarFichasArribaAux(fila,columna,movimiento.getFichaJugador());
                }catch (ArrayIndexOutOfBoundsException ex){
                    repetir=false;
                }
            }while (repetir);
        }
    }

    /**
     * Método auxiliar a cambiarFichasArriba.
     * @param fila Fila donde se ha de efectuar el cambio.
     * @param columna Columna donde se ha de efectuar el cambio.
     * @param deseada Ficha que tiene que estar en esa fila y columna.
     * @return Devuelve "true" se ha de parar.
     * @throws ArrayIndexOutOfBoundsException En caso de que se salga del array se lanza la excepción.
     */
    private boolean cambiarFichasArribaAux(int fila,int columna, Ficha deseada) throws ArrayIndexOutOfBoundsException{
        if(fila<0){
            throw new ArrayIndexOutOfBoundsException();
        } else if (this.casillas[fila - 1][columna].isEmpty()) {
            Ficha ocupa=this.casillas[fila][columna].getFicha();
            this.casillas[fila-1][columna].setFicha(ocupa);
            this.casillas[fila][columna].setFicha(deseada);
            return false;
        }else{
            Ficha ocupa=this.casillas[fila][columna].getFicha();
            this.casillas[fila][columna].setFicha(deseada);
            return this.cambiarFichasArribaAux((fila-1),columna,ocupa);
        }
    }

    /**
     * Método para borrar una ficha y su columna superior.
     * @param coordenadas Coordenadas de la ficha a borrar.
     */
    public void borrarFicha(Coordenadas coordenadas){
        if(!(this.isEmpty(coordenadas.getFila(),coordenadas.getColumna()))){
            this.borrarColumna(coordenadas); // Borramos la columna superior, ya que es posterior a la ficha que queremos eliminar.
        }
    }

    /**
     * Método para borrar la columna superior a una ficha (incluido) mediante sus coordenadas.
     * @param coordenadas Coordenadas de la ficha a borrar.
     */
    private void borrarColumna(Coordenadas coordenadas){
        int fila= coordenadas.getFila();
        int columna=coordenadas.getColumna();
        boolean repetir=true;
        do{
            try {
                if (this.casillas[fila][columna].isEmpty()) {
                    repetir = false;
                } else {
                    this.casillas[fila][columna].setFicha(new Ficha()); // Pone la ficha en blanco (vacío).
                    fila--;
                }
            }catch (ArrayIndexOutOfBoundsException ex){ // Si nos salimos del array.
                repetir=false;
            }
        }while (repetir);
    }

    /**
     * Método para reponer una ficha en determinadas coordenadas.
     * @param coordenadas Coordenadas de la ficha a reponer.
     * @param ficha Ficha a reponer
     * @throws CasillaIsNotEmptyException Si la casilla está ocupada entonces se lanzará esta excepción.
     */
    public void reponerFicha(Coordenadas coordenadas, Ficha ficha) throws CasillaIsNotEmptyException {
        if(this.casillas[coordenadas.getFila()][coordenadas.getColumna()].isEmpty()){
            this.casillas[coordenadas.getFila()][coordenadas.getColumna()].setFicha(ficha);
        }else{
            throw new CasillaIsNotEmptyException();
        }
    }

    public static void main(String[] args) {
        Tablero t1= new Tablero(6,7);
        t1.dibujar();
        t1.ponerFicha(new Ficha('P', Color.BLUE));
        t1.dibujar();
    }

}
