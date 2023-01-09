package etsisi.juego;

import java.util.ArrayList;
import java.util.Random;


/**
 * Clase que hereda de Jugador que se destinará a hacer las veces de un jugador máquina.
 */
public class JugadorIA extends Jugador{
    // ATRIBUTOS
    ArrayList<Integer> columnasDisponibles;
    // CONSTRUCTOR
    public JugadorIA(Ficha ficha,Tablero tablero) {
        super("IA", ficha);
        this.columnasDisponibles= Inicios.inicializarArrayList(tablero.getNumColumnas());

    }

    // MÉTODOS
     @Override
    public Coordenadas poner(Tablero tablero)  {
        Random random=new Random();
        int columna;
        boolean repetir=true;
        Coordenadas posicion=null;
        while(repetir) {
            //
            columna = random.nextInt((tablero.getNumColumnas())-1);
            if (this.columnasDisponibles.contains(columna)) {
                try {
                    this.animacionCarga();
                    int fila = tablero.caeFichaIA(columna);
                    posicion = tablero.ponerFichaIA(fila, columna, super.getFicha());
                    repetir = false;
                } catch (ColumnaLlenaException ex) {
                    this.columnasDisponibles.remove(new Integer(columna));
                    if(this.columnasDisponibles.isEmpty()){
                        Viewer.printString(super.ERROR_TABLERO_LLENO);
                        repetir=false; //En este caso tenemos que salir del bucle porque el tablero está lleno
                    }
                }catch (InterruptedException ex){
                   Viewer.printString(ex.getMessage());
                }
            }
        }
        return posicion;
    }

    /**
     * Este método sirve para hacer la animación de carga de la puesta de ficha en el tablero.
     * @throws InterruptedException
     */
    private void animacionCarga() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Viewer.print(".");
            Thread.sleep(500);
        }
       Viewer.introducirSaltoLinea();
    }



}
