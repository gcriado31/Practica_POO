package etsisi.juego;

import etsisi.pilas.DLCircularStack;
import etsisi.pilas.DLStack;
import etsisi.pilas.StackEmptyException;

/**
 * En esta clase se desarrollará en modo de juego Demo.
 */
public class ModoEnfrentamiento extends ModoJuegoHumano {
    // ATRIBUTOS

    private final int INICIO_BUCLE=0;
    private final String NO_MOVIMIENTOS="Este jugador no tiene movimientos para rehacer";

    //CONSTRUCTOR
    public ModoEnfrentamiento() {
        super();
    }

    // MÉTODOS IMPLEMENTDADOS
    @Override
    public void jugar() {
        boolean finJuego=false;
        boolean finModo;
        super.dibujar();
        do {
            while (!finJuego) {
                Viewer.printString(("Turno de: " +super.turno.nombreJugadorConTurno()+" (Fichas restantes: "+super.turno.tieneTurno().fichasRestantes+")"));
                finJuego=super.opciones();
            }
            finModo=super.fin();
            if(finModo){
                super.nuevaPartida();
                finJuego=false;
            }
        }while (finModo);
        super.finModo();
    }

    @Override
    protected void resultados() {
        if(super.ganador!=null){
            Viewer.printString(super.GANADOR+super.ganador.getNombre()+" ¡ENHORABUENA!");
        }else{
            Viewer.printString(super.EMPATE);
        }
        Viewer.printString(super.RESULTANTE);
        super.dibujar();
    }

    @Override
    protected DLCircularStack<Jugador> menuJugadores() {
        DLCircularStack<Jugador> jugadors = new DLCircularStack<>();
        for (int i = this.INICIO_BUCLE; i <super.NUMERO_JUGADORES; i++) {
            int pos=i+1;
            Viewer.printString("---JUGADOR "+pos+"---");
            if(i==0){
                jugadors.push(new Jugador(super.infoJugador(),super.fichaAzul));
                Viewer.printString("Se le ha asignado la ficha azul");
            }else{
                jugadors.push(new Jugador(super.infoJugador(),super.fichaRoja));
                Viewer.printString("Se le ha asignado la ficha roja");
            }
        }
        return jugadors;
    }

    /**
     * Método para el patrón undo.
     */
    @Override
    protected void undo() {
        Movimiento movimientoBorrar=this.sacarMovientoJugador(super.turno.tieneTurno().getFicha());
        if(movimientoBorrar!=null) {
            super.tablero.quitarFicha(movimientoBorrar.getCoordenadasFichaIntroducida());
            super.movimientosSacados.push(movimientoBorrar);
        }else{
            Viewer.printString(NO_RETROCEDIDO);
        }
    }

    private Movimiento sacarMovientoJugador(Ficha fichaJugador){
        DLStack<Movimiento> movimientosOtroJugador=new DLStack<>();
        Movimiento devuelto=null;
        boolean encontrado=false;
        while(!encontrado){
            try {
                devuelto=super.movimientos.pop();
                if(devuelto.getFichaJugador().equals(fichaJugador)){
                    encontrado=true;
                    this.trasnferirMovimientos(movimientosOtroJugador,super.movimientos);
                }else{
                    movimientosOtroJugador.push(devuelto);
                }
            } catch (StackEmptyException e) {
                Viewer.printString(NO_MOVIMIENTOS);
                this.trasnferirMovimientos(movimientosOtroJugador,super.movimientos);
                devuelto=null;
                encontrado=true; // Se para el bucle, ya que no hay más movimientos donde buscar.
            }
        }
        return devuelto;
    }

    private void trasnferirMovimientos(DLStack<Movimiento> pilaOrigen, DLStack<Movimiento> pilaDestino){
        boolean repetir=true;
        while(repetir){
            try {
                pilaDestino.push(pilaOrigen.pop());
            } catch (StackEmptyException e) {
                repetir=false;
            }
        }
    }

    /**
     * Método para el patrón redo.
     */
    @Override
    protected void redo() {
        try {
            Movimiento movimientoRehacer= super.movimientosSacados.pop();
            super.tablero.cambiarFichasArriba(movimientoRehacer);
            super.movimientos.push(movimientoRehacer);
        } catch (StackEmptyException e) {
            Viewer.printString(super.NO_REHECHO);
        }
    }

    @Override
    public void sumarFichasRestantes(){
        super.turno.tieneTurno().fichasRestantes++;
    }

    @Override
    public void restarFichasRestantes(){
        super.turno.tieneTurno().fichasRestantes--;
    }
}
