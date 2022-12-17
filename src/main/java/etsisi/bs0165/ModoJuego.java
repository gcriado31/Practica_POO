package etsisi.bs0165;

import java.awt.*;
import java.util.Scanner;

/**
 * Esta interfaz contiene los métodos comunes (y mínimos) de los cuatro modos de juego: Demo, Enfrentamiento y Entrenamiento.
 */
public abstract class ModoJuego {

    // ATRIBUTOS
    protected Validaciones reglas;
    protected Tablero tablero;
    protected final String BIENVENIDA="Bienvenido a Conecta 4\nConsigue conectar 4 fichas en linea o en diagonal para ganar";
    protected final String EMPATE="¡¡¡EMPATE!!!";
    protected final String GANADOR="EL GANADOR ES ";
    protected final int NUMERO_JUGADORES=2;
    protected final Ficha fichaAzul=new Ficha('A', Color.BLUE);
    protected final Ficha fichaRoja= new Ficha('R',Color.BLUE);

    // ATRIBUTOS PRIVADOS
    private final int NUM_FILAS=6;
    private final int NUM_COLUMNAS=7;

    // CONSTRUCTOR

    public ModoJuego(Tablero tablero) {
        this.tablero = tablero;
        this.reglas=new Validaciones(tablero);
    }


    // MÉTODOS ABSTRACTOS
    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará una nueva partida.
     */
    protected abstract void jugar();

    /**
     * Cambia el turno.
     */
    protected abstract void cambiaTurno();

    /**
     * Presenta los resultados
     */
    protected abstract void resultados();

    /**
     * Se actualiza el tablero en los jugadores.
     * @param tablero Se pasa el tablero que se quiere actualizar.
     */
    protected abstract void actualizaTablero(Tablero tablero);

    /**
     * Da la bienvenida a los jugadores y almacena los jugadores.
     * @return Se devuelve el array de jugadores con toda la información.
     */
    protected abstract Jugador[] menuJugadores();

    /**
     * Llama al método hayGanador de tablero para saber si hay ganador.
     * @return Devolverá "true" si hay ganador, si no "false".
     */
    protected abstract boolean hayGanador(Coordenadas coordenadas);



    // MÉTODOS DE LA CLASE
    /**
     * Carga una nueva partida.
     */
    protected void nuevaPartida(){
        this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
        actualizaTablero(tablero);
        System.out.println("\n\n----- NUEVA PARTIDA ------\n");
    }


    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida.
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false".
     */
    protected boolean fin(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿DESEA VOLVER A JUGAR ESTE MODO?(S/N)");
        char respuesta= input.nextLine().toUpperCase().charAt(0);
        return respuesta=='S';
    }

    /**
     * Dibuja el tablero.
     */
    protected void dibujar(){tablero.dibujar();}

    /**
     * Pregunta la información al jugador.
     * @return Se devuelve un String con la información del juegador.
     */
    protected String infoJugador(){
        Scanner input= new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        String nombre= input.nextLine();
        System.out.println();
        return nombre;
    }

}
