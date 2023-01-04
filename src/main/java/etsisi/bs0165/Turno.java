package etsisi.bs0165;
import etsisi.pilas.*;

/**
 * Esta clase controla los turnos de la partida.
 * Está dispuesto de forma cíclica de forma que cuando llega al último jugador vuelve al primero.
 */
public class Turno {
    // ATRIBUTOS
    protected int jugadorEnCurso;
    private final int JUGADOR_INICIAL = 0;
    private DLCircularStack<Jugador> jugadores;
    private IteratorDLCircularStack<Jugador> iteradorJugadores;

    // CONSTRUCTORES
    public Turno (DLCircularStack<Jugador>  jugadores){
        this.jugadores=jugadores;
        this.jugadorEnCurso=JUGADOR_INICIAL;
        this.iteradorJugadores=new IteratorDLCircularStack<>(jugadores);
    }

    // MÉTODOS

    /**
     * Calcula el jugador siguiente.
     * @return Devolverá el valor del jugador siguiente en caso de que se haya llegado al final se devuleve 0 (vuelve al principio).
     */
    private int calculaSiguienteJugador(){
        jugadorEnCurso++;
        if(jugadorEnCurso>=jugadores.size()){   // COMPROBAMOS QUE jugadorEnCurso LLEGA AL FINAL PARA VOLVER AL JUGADOR INICIAL Y ESTABLECER UNA NUEVA RONDA
             return JUGADOR_INICIAL;
        }else{
            return jugadorEnCurso;
        }
    }

    /**
     * Cambia el turno de los jugadores.
     */
    public void cambiaTurno(){
        try {
            this.iteradorJugadores.next();
            this.jugadorEnCurso=calculaSiguienteJugador();
        }catch (StackEmptyException ex){
            System.out.println(ex.getMessage());
        }
    }

    public String nombreJugadorConTurno(){
        try {
            return this.iteradorJugadores.getInfo().getNombre();
        } catch (StackEmptyException e) {
            System.out.println(e.getMessage());
            return "NO NAME";
        }
    }



    public Jugador tieneTurno(){
        try {
            return iteradorJugadores.getInfo();
        } catch (StackEmptyException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}