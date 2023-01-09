package etsisi.juego;

import etsisi.pilas.*;




/**
 * Clase abstracta que nos dice cómo van a funcionar los modos de juego que tienen patron undo redo.
 * Hereda de Modo juego, ya que los métodos esenciales son comunes para todos los modos.
 */
public abstract class ModoJuegoHumano extends ModoJuego{
    // ATRIBUTOS
    protected DLStack<Movimiento> movimientos;

    protected DLStack<Movimiento> movimientosSacados;
    protected final String RETROCEDIDO="Se ha retrocedido un movimiento";
    protected final String NO_RETROCEDIDO="No se pueden deshacer más movimientos.";
    protected final String REHECHO="Se ha rehecho un movimiento";
    protected final String NO_REHECHO="No se pueden reponer más movimientos";
    private final String ELECCION_BASICA =("Eliga que hacer:\n" +
                                            "\t1. Poner ficha.\n");
    private final String OPCION_2="\t2. Deshacer movimiento.\n";
    private final String OPCION_3="\t3. Rehacer movimiento.\n";
    private final String OPCION_0="\t0. Rendirse.";
    private final String TECLA_INCORRECTA="TECLA INCORRECTA";

    // CONSTRUCTOR
    protected ModoJuegoHumano() {
        super();
        this.movimientos=new DLStack<>();
        this.movimientosSacados=new DLStack<>();
    }

    //MÉTODOS ABSTACTOS

    /**
     * Método para el patrón undo.
     */
    protected abstract void undo();

    /**
     * Método para el patrón redo.
     */
    protected abstract void redo();

    /**
     * Método para sumar la(s) ficha(s) que se descuenten en el undo.
     */
    protected abstract void sumarFichasRestantes();

    /**
     * Método para restar la(s) ficha(s) que se añadan el redo.
     */
    protected abstract void restarFichasRestantes();

    //MÉTODOS DE LA CLASE
    /**
     * Método para que el usuario elija qué hacer.
     * @return Devuelve la elección del usuario.
     */
    protected char eleccionMovimiento(){
        return Viewer.options(this.mostarElecciones());
    }

    private String mostarElecciones(){
        String devuelto=ELECCION_BASICA;
        if(this.movimientos.size()>=2){
           devuelto+=OPCION_2;
        }
        if(this.movimientosSacados.size()>=1){
            devuelto+=OPCION_3;
        }
        devuelto+=OPCION_0;
        return devuelto;
    }

    /**
     * Manejará lo que haya seleccionado el usuario.
     * @return Devolverá si el usuario quiere finalizar o ha finalizado el juego (victoria o empate).
     */
    protected boolean opciones(){
        boolean finJuego=false;
        switch (eleccionMovimiento()){
            case'1':
                super.dibujar();
                finJuego=this.ponerFicha();
                if(movimientosSacados.size()>0) {
                    this.vaciarMovimientosSacados();
                }
                break;
            case '2':
                this.undo();
                super.tablero.dibujar();
                sumarFichasRestantes();
                break;
            case'3':
                this.redo();
                super.tablero.dibujar();
                restarFichasRestantes();
                break;
            case '0':
                finJuego=true;
                break;
            default:
                Viewer.printString(TECLA_INCORRECTA);
                break;
        }
        return finJuego;
    }

    @Override
    protected boolean ponerFicha(){ // Reescribimos el método para poder almacenar el movimiento.
        boolean finJuego=false;
        Coordenadas posicion = null ;
        try {
            posicion=super.turno.tieneTurno().poner(super.tablero);
            this.movimientos.push(new Movimiento(super.turno.tieneTurno().getFicha(),posicion));
        }catch (SinFichasException ex) {
            Viewer.printString(ex.getMessage());
            finJuego = true;
        }finally {
            if (posicion!=null) {
                super.reglas.setTablero(tablero);
                if (super.hayGanador(posicion)) {
                    finJuego = true;
                    super.ganador = super.turno.tieneTurno();
                    this.resultados();
                } else if (super.tablero.tableroLleno()) {
                    finJuego = true;
                    super.ganador = null;
                    this.resultados();
                } else {
                    super.dibujar();
                    super.cambiaTurno();
                }
            }
        }
        return finJuego;
    }

    /**
     * Crea una nueva partida y vacía las pilas de movimientos.
     */
    @Override
    public void nuevaPartida(){
        this.vaciarMovimientosSacados();
        this.vaciarMovimientos();
        super.nuevaPartida();
    }

    /**
     * Método para el fin del modo donde se vacían las pilas de movimientos
     */
    protected void finModo(){
        vaciarMovimientos();
        vaciarMovimientosSacados();
    }

    /**
     * Vacía la pila de movimientosSacados.
     */
    private void vaciarMovimientosSacados(){
        if (!this.movimientosSacados.isEmpty()){
            this.movimientosSacados.vacuum();
        }
    }

    /**
     * Vacía la pila de movimientos.
     */
    private void vaciarMovimientos(){
        if (!this.movimientos.isEmpty()){
            this.movimientos.vacuum();
        }
    }

}
