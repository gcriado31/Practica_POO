package etsisi.bs0165;

/**
 * Esta clase controla los turnos de la partida.
 * Está dispuesto de forma cíclica de forma que cuando llega al último jugador vuelve al primero.
 */
public class Turno {
    // ATRIBUTOS
    protected int jugadorEnCurso;
    private final int JUGADOR_INICIAL = 0;
    private Jugador[] jugadores;

    // CONSTRUCTORES
    public Turno (Jugador []  jugadores){
        this.jugadores=jugadores;
        this.jugadorEnCurso=JUGADOR_INICIAL;
    }

    // MÉTODOS

    /**
     * Calcula el jugador siguiente.
     * @return Devolverá el valor del jugador siguiente en caso de que se haya llegado al final se devuleve 0 (vuelve al principio).
     */
    private int calculaSiguienteJugador(){
        jugadorEnCurso++;
        if(jugadorEnCurso>=jugadores.length){   // COMPROBAMOS QUE jugadorEnCurso LLEGA AL FINAL PARA VOLVER AL JUGADOR INICIAL Y ESTABLECER UNA NUEVA RONDA
             return JUGADOR_INICIAL;
        }else{
            return jugadorEnCurso;
        }
    }

    /**
     * Cambia el turno de los jugadores.
     */
    public void cambiaTurno(){
        jugadorEnCurso=calculaSiguienteJugador();
    }

    public String nombreJugadorConTurno(){
        return jugadores[jugadorEnCurso].getNombre();
    }

    public char fichaJugadorConTurno(){
        return jugadores[jugadorEnCurso].getFicha().getContenido();
    }

    public Jugador tieneTurno(){
        return jugadores[jugadorEnCurso];
    }

}