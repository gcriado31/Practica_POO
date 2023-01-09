package etsisi.juego;

import etsisi.pilas.*;

import java.awt.*;


/**
 * Esta clase abstracta contiene los métodos comunes (y mínimos) de los cuatro modos de juego: Demo, Enfrentamiento y Entrenamiento.
 */
public abstract class ModoJuego {

    // ATRIBUTOS
    protected Turno turno;
    protected DLCircularStack<Jugador> jugadores;
    protected Jugador ganador;
    protected Validaciones reglas;
    protected Tablero tablero;
    protected final String EMPATE="¡¡¡EMPATE!!!";
    protected final String GANADOR="EL GANADOR ES ";
    protected final String RESULTANTE="Tablero resultante ";
    private final String VOLVER_A_JUGAR_MODO="¿DESEA VOLVER A JUGAR ESTE MODO?(S/N)";
    private final String NUEVA_PARTIDA="\n\n----- NUEVA PARTIDA ------\n";
    private final String INTRODUCIR_NOMBRE="Introduzca su nombre: ";
    protected final int NUMERO_JUGADORES=2;
    protected final Ficha fichaAzul=new Ficha('A', Color.BLUE);
    protected final Ficha fichaRoja= new Ficha('R',Color.RED);

    // ATRIBUTOS PRIVADOS
    protected static final int NUM_FILAS=6;
    protected static final int NUM_COLUMNAS=7;

    // CONSTRUCTOR

    protected ModoJuego() {
        this.tablero = Inicios.nuevoTablero(NUM_FILAS,NUM_COLUMNAS);
        this.reglas=new Validaciones(tablero);
        this.jugadores=this.menuJugadores();
        this.turno=new Turno(this.jugadores);
    }


    // MÉTODOS ABSTRACTOS
    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará una nueva partida.
     */
    protected abstract void jugar();

    /**
     * Presenta los resultados
     */
    protected abstract void resultados();

    /**
     * Da la bienvenida a los jugadores y almacena los jugadores.
     * @return Se devuelve el array de jugadores con toda la información.
     */
    protected abstract DLCircularStack<Jugador> menuJugadores();

    // MÉTODOS DE LA CLASE
    /**
     * Carga una nueva partida.
     */
    protected void nuevaPartida(){
        this.tablero=Inicios.nuevoTablero(NUM_FILAS,NUM_COLUMNAS);
        Viewer.printString(NUEVA_PARTIDA );
    }


    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida.
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false".
     */
    protected boolean fin(){
        char respuesta= Viewer.options(VOLVER_A_JUGAR_MODO);
        return respuesta=='S';
    }

    /**
     * Dibuja el tablero.
     */
    protected void dibujar(){this.tablero.dibujar();}

    /**
     * Pregunta la información al jugador.
     * @return Se devuelve un String con la información del juegador.
     */
    protected String infoJugador(){
        return Viewer.readLine(INTRODUCIR_NOMBRE);
    }


    /**
     * Cambia el turno.
     */
    protected void cambiaTurno() {this.turno.cambiaTurno();}

    /**
     * Llama al método hayGanador de tablero para saber si hay ganador.
     * @return Devolverá "true" si hay ganador, si no "false".
     */
    protected boolean hayGanador(Coordenadas coordenadas) {
        return reglas.hayGanador(turno.tieneTurno().getFicha(), coordenadas);
    }

    /**
     * El jugador pone la ficha y se comprueba si gana o empata y si no, se cambia el turno.
     * @return Devuelve si la partida termina.
     */
    protected boolean ponerFicha(){
        boolean finJuego=false;
        this.dibujar();
        Coordenadas posicion = null ;
        try {
            posicion=this.turno.tieneTurno().poner(this.tablero);
        }catch (SinFichasException ex) {
            Viewer.printString(ex.getMessage());
            finJuego = true;
        }finally {
            if (posicion!=null) {
                this.reglas.setTablero(tablero);
                if (this.hayGanador(posicion)) {
                    finJuego = true;
                    this.ganador = this.turno.tieneTurno();
                    this.resultados();
                } else if (this.tablero.tableroLleno()) {
                    finJuego = true;
                    this.ganador = null;
                    this.resultados();
                } else {
                    this.cambiaTurno();
                }
            }
        }
        return finJuego;
    }

}
