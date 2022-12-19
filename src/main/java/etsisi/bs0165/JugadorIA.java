package etsisi.bs0165;

import java.util.ArrayList;
import java.util.Random;

// TODO: Algoritmo para poner la ficha de modo que le interese para ganar.

/**
 * Clase que hereda de Jugador que se destinará a hacer las veces de un jugador máquina.
 */
public class JugadorIA extends Jugador{
    // ATRIBUTOS
    ArrayList<Integer> columnasDisponibles;
    // CONSTRUCTOR
    public JugadorIA(Ficha ficha, Tablero tablero) {
        super("IA", ficha,tablero);
        this.columnasDisponibles= Inicios.inicializarArrayList(tablero.getNumColumnas());

    }

    // MÉTODOS
     @Override
    public Coordenadas poner() throws SinFichasException {
        Random random=new Random();
        int columna;
        boolean repetir=true;
        Coordenadas posicion=null;
        while(repetir) {
            columna = random.nextInt((super.tablero.getNumColumnas()) - 1);
            if (this.columnasDisponibles.contains(columna)) {
                try {
                    this.animacionCarga();
                    int fila = super.tablero.caeFichaIA(columna);
                    posicion = super.tablero.ponerFichaIA(fila, columna, super.getFicha());
                    super.setFichasRestantes((super.fichasRestantes-1));
                    if(super.fichasRestantes==0){
                        throw new SinFichasException(super.nombre);
                    }
                    repetir = false;
                } catch (ColumnaLlenaException ex) {
                    this.columnasDisponibles.remove(new Integer(columna));
                    if(this.columnasDisponibles.isEmpty()){
                        System.out.println(super.ERROR_TABLERO_LLENO);
                        repetir=false; //En este caso tenemos que salir del bucle porque el tablero está lleno
                    }
                }catch (InterruptedException ex){
                    System.out.println(ex.getMessage());
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
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println();
    }



}
