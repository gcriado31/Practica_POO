package etsisi.juego;
import etsisi.pilas.*;

/**
 * En esta clase se desarrollará en modo de juego Entrenamiento.
 */
public class ModoEntrenamiento extends ModoJuegoHumano {
    //ATRIBUTOS


    // CONSTRUCTOR
    public ModoEntrenamiento() {
        super();
    }


    // METODOS IMPLEMENTADOS
    @Override
    public void jugar() {
        boolean finJuego=false;
        boolean finModo;
        do{
            super.dibujar();
            while(!finJuego) {
                Viewer.printString(("Turno de: " +super.turno.nombreJugadorConTurno()+" (Fichas restantes: "+super.turno.tieneTurno().fichasRestantes+")"));
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
        super.finModo();
    }
    @Override
    protected void resultados() {
        if(super.ganador!=null){
            if(super.ganador.nombre.equals("IA")){
                Viewer.printString((super.GANADOR+" LA "+this.ganador.getNombre()+"\nTe ha ganado la IA, tienes que mejorar"));
            }else{
                Viewer.printString((super.GANADOR+super.ganador.getNombre()+" ¡ENHORABUENA!"));
            }
        }else{
            Viewer.printString(super.EMPATE);
        }
        Viewer.printString(super.RESULTANTE);
        super.dibujar();

    }
    @Override
    protected DLCircularStack<Jugador> menuJugadores() {
        DLCircularStack<Jugador> jugadors= new DLCircularStack<Jugador>();
        for (int i = 0; i < super.NUMERO_JUGADORES; i++) {
            int pos=i+1;
            Viewer.printString(("---JUGADOR "+pos+"---"));
            jugadors.push(new Jugador(super.infoJugador(),super.fichaAzul));
            i++; // Aquí sumamos uno para poder altenar en un ciclo del bucle.
            if(i<super.NUMERO_JUGADORES) {
                jugadors.push(new JugadorIA(super.fichaRoja, super.tablero));
            }
        }

        return jugadors;
    }



    /**
     * Método que tiene el patrón undo del modo de juego entrenamiento.
     */
    @Override
    protected void undo() {
        // Deshacemos el movimiento de la IA y del jugador.
        try {
            // El de la IA.
            Movimiento movimientoBorrar =super.movimientos.pop();
            super.tablero.borrarFicha(movimientoBorrar.getCoordenadasFichaIntroducida());
            super.movimientosSacados.push(movimientoBorrar);
            // El del jugador.
            movimientoBorrar =super.movimientos.pop();
            super.tablero.borrarFicha(movimientoBorrar.getCoordenadasFichaIntroducida());
            super.movimientosSacados.push(movimientoBorrar);
            Viewer.printString(super.RETROCEDIDO);
        } catch (StackEmptyException e) {
            Viewer.printString(super.NO_RETROCEDIDO);
        }
    }

    /**
     * Método que tiene el patrón redo del modo de juego entrenamiento.
     */
    @Override
    protected void redo() {
        // Rehacemos el movimiento de la IA y el del jugador.
        try {
            // El del jugador.
            Movimiento movimientoReponer =super.movimientosSacados.pop();
            super.tablero.reponerFicha(movimientoReponer.getCoordenadasFichaIntroducida(),movimientoReponer.getFichaJugador());
            super.movimientos.push(movimientoReponer);
            // El de la IA.
            movimientoReponer =super.movimientosSacados.pop();
            super.tablero.reponerFicha(movimientoReponer.getCoordenadasFichaIntroducida(),movimientoReponer.getFichaJugador());
            super.movimientos.push(movimientoReponer);
            Viewer.printString(super.REHECHO);
        } catch (StackEmptyException e) {
            Viewer.printString(super.NO_REHECHO);
        } catch (CasillaIsNotEmptyException e) {
            Viewer.printString(e.getMessage());
        }
    }

    @Override
    public void sumarFichasRestantes(){
       IteratorDLCircularStack<Jugador> it = new IteratorDLCircularStack<Jugador>(super.jugadores);
       boolean repetir=true;
       do {
           try {
               int fichas = it.getInfo().fichasRestantes;
               fichas++;
               it.getInfo().setFichasRestantes(fichas);
               it.next();
           } catch (StackEmptyException e) {
               repetir = false;
           }
       }while (repetir);
    }

    @Override
    public void restarFichasRestantes(){
        IteratorDLCircularStack<Jugador> it = new IteratorDLCircularStack<Jugador>(super.jugadores);
        boolean repetir=true;
        do {
            try {
                int fichas = it.getInfo().fichasRestantes;
                fichas--;
                it.getInfo().setFichasRestantes(fichas);
                it.next();
            } catch (StackEmptyException e) {
                repetir = false;
            }
        }while (repetir);
    }

    public static void main(String[] args) {
        ModoEntrenamiento modo= new ModoEntrenamiento();
        modo.jugar();
    }

}
