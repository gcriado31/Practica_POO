package etsisi.bs0165;
import etsisi.pilas.*;

/**
 * En esta clase se desarrollará en modo de juego Entrenamiento.
 */
public class ModoEntrenamiento extends ModoJuegoIA{
    //ATRIBUTOS
    private Jugador ganador;

    // CONSTRUCTOR
    public ModoEntrenamiento(Tablero tablero) {
        super(tablero);
    }


    // METODOS IMPLEMENTADOS
    @Override
    public void jugar() {
        boolean finJuego=false;
        boolean finModo;
        do{
            super.dibujar();
            while(!finJuego) {
                System.out.println("Turno de: " +super.turno.nombreJugadorConTurno());
                if(super.turno.tieneTurno().getClass().equals(JugadorIA.class)){ //Si el jugador es una IA pondrá la ficha directamente.
                    finJuego=super.ponerFicha();
                }else { // Si el jugador es humano se le preguntará qué quiere hacer.
                    finJuego = super.opciones();
                }
            }
            finModo=super.fin();
            if(finModo){
                super.nuevaPartida();
                finJuego=false;
            }
        }while(finModo);

    }
    @Override
    protected void resultados() {
        if(this.ganador!=null){
            if(this.ganador.nombre.equals("IA")){
                System.out.println(super.GANADOR+" LA "+this.ganador.getNombre()+"\nTe ha ganado la IA, tienes que mejorar");
            }else{
                System.out.println(super.GANADOR+this.ganador.getNombre()+" ¡ENHORABUENA!");
            }
        }else{
            System.out.println(super.EMPATE);
        }
        System.out.println("Tablero resultante ");
        super.dibujar();

    }
    @Override
    protected DLCircularStack<Jugador> menuJugadores(Tablero tablero) {
        DLCircularStack<Jugador> jugadors= new DLCircularStack<Jugador>();
        for (int i = 0; i < super.NUMERO_JUGADORES; i++) {
            int pos=i+1;
            System.out.println("---JUGADOR "+pos+"---");
            jugadors.push(new Jugador(super.infoJugador(),super.fichaAzul,tablero));
            i++; // Aquí sumamos uno para poder altenar en un ciclo del bucle.
            if(i<super.NUMERO_JUGADORES) {
                jugadors.push(new JugadorIA(super.fichaRoja, tablero));
            }
        }

        return jugadors;
    }



    /**
     * Método que tiene el patrón undo del modo de juego entrenamiento.
     */
    @Override
    protected void undo() {
        // Deshacemos el movimiento de la IA y el del jugador.
        try {
            this.dibujarTableros();
            super.movimientosSacados.push(super.movimientos.pop()); // El del jugador.
            super.movimientosSacados.push(super.movimientos.pop()); // El de la IA.
            System.out.println("Se ha retrocedido un movimiento\n\tQuedan :"+super.movimientos.size()+" movimientos");
            Tablero tablero=super.movimientos.top();
            tablero.dibujar();
            super.actualizaTablero(tablero); // Actualizamos el tablero al tablero anterior al movimiento del jugador
        } catch (StackEmptyException e) {
            System.out.println("No se puede retroceder más");
        }
    }

    /**
     * Método que tiene el patrón redo del modo de juego entrenamiento.
     */
    @Override
    protected void redo() {
        // Rehacemos el movimiento de la IA y el del jugador.
        try {
            super.movimientos.push(super.movimientosSacados.pop()); // El del jugador.
            super.movimientos.push(super.movimientosSacados.pop()); // El de la IA.
            System.out.println("Se ha avanzado un movimiento.\n\tQuedan :"+super.movimientosSacados.size()+" movimientos por rehacer");
            Tablero tablero=super.movimientos.top();
            tablero.dibujar();
            super.actualizaTablero(super.movimientos.top()); // Actualizamos el tablero al tablero.
        } catch (StackEmptyException e) {
            System.out.println("No se pueden rehacer más movimientos");
        }
    }

    public void dibujarTableros(){
        IteratorDLStack<Tablero> it=new IteratorDLStack<Tablero>(super.movimientos);
        for (int i = 0; i < super.movimientos.size(); i++) {
            try {
                System.out.println("Tablero numero"+(i+1));
                it.getInfo().dibujar();
            } catch (NullInfoException e) {
                System.out.println("No hay tablero");
            }
        }
    }

    public static void main(String[] args) {
        ModoEntrenamiento modo= new ModoEntrenamiento(new Tablero(6,7));
        modo.jugar();
    }

}
