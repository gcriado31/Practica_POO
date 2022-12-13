package etsisi.bs0165;

/**
 * Esta interfaz contiene los métodos comunes (y mínimos) de los cuatro modos de juego: Demo, Enfrentamiento y Entrenamiento.
 */
public interface ModoJuego {

    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará una nueva partida.
     */
    void jugar();

    /**
     * Carga una nueva partida.
     */
    void nuevaPartida();

    /**
     * Cambia el turno.
     */
    void cambiaTurno();

    /**
     * Llama al método hayGanador de tablero para saber si hay ganador.
     * @return Devolverá "true" si hay ganador, si no "false".
     */
    boolean hayGanador(Coordenadas coordenadas);

    /**
     * Presenta los resultados
     */
    void resultados();

    /**
     * Se actualiza el tablero en los jugadores.
     * @param tablero Se pasa el tablero que se quiere actualizar.
     */
    void actualizaTablero(Tablero tablero);

    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida.
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false".
     */
    boolean fin();

    /**
     * Dibuja el tablero.
     */
    void dibujar();

}
