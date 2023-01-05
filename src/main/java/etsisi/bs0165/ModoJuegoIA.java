package etsisi.bs0165;

import etsisi.pilas.DLStack;
import etsisi.pilas.StackEmptyException;

import java.util.Scanner;

/**
 * Clase abstracta que nos dice cómo van a funcionar los modos de juego que tienen una IA.
 * Hereda de Modo juego ya que los métodos esenciales son comunes para todos los modos.
 */
public abstract class ModoJuegoIA extends ModoJuego{
    // ATRIBUTOS
    protected DLStack<Tablero> movimientos;
    protected DLStack<Tablero> movimientosSacados;

    // CONSTRUCTOR
    protected ModoJuegoIA(Tablero tablero) {
        super(tablero);
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

    //MÉTODOS DE LA CLASE
    /**
     * Método para que el usuario elija qué hacer.
     * @return Devuelve la elección del usuario.
     */
    protected char eleccionMovimiento(){
        System.out.println("Eliga que hacer:\n" +
                "\t1. Poner ficha.");
        if(this.movimientos.size()>=2){
            System.out.println("\t2. Deshacer movimiento.");
        }
        if(this.movimientosSacados.size()>=2){
            System.out.println("\t3. Rehacer movimiento");
        }
        System.out.println("\t0. Rendirse.");
        Scanner input=new Scanner(System.in);
        return input.next().charAt(0);
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
                break;
            case '2':
                this.undo();
                finJuego=opciones();
                this.vaciarMovimientosSacados();
                break;
            case'3':
                this.redo();
                finJuego=opciones();
                break;
            case '0':
                finJuego=true;
                break;
            default:
                System.out.println("TECLA INCORRECTA");
                break;
        }
        return finJuego;
    }

    @Override
    protected boolean ponerFicha(){ // Reescribimos el método para poder almacenar el movimiento.
        boolean finJuego=false;
        Coordenadas posicion = null ;
        try {
            posicion=super.turno.tieneTurno().poner();
        }catch (SinFichasException ex) {
            System.out.println(ex.getMessage());
            finJuego = true;
        }finally {
            if (posicion!=null) {
                super.reglas.setTablero(tablero);
                if (super.hayGanador(posicion)) {
                    finJuego = true;
                    super.ganador = super.turno.tieneTurno();
                    this.resultados();
                } else if (this.tablero.tableroLleno()) {
                    finJuego = true;
                    super.ganador = null;
                    this.resultados();
                } else {
                    super.dibujar();
                    super.actualizaTablero(super.turno.tieneTurno().getTablero());
                    Tablero movimiento=Inicios.copiaTablero(super.tablero);
                    try {
                        System.out.println("\nTablero de TOP antes de pushear");
                        this.movimientos.top().dibujar();
                        System.out.println("\n");
                    } catch (StackEmptyException e) {
                        System.out.println("No hay tablero");
                    }
                    this.movimientos.push(movimiento);
                    super.cambiaTurno();
                }
            }
        }
        return finJuego;
    }

    @Override
    public void nuevaPartida(){
        this.vaciarMovimientosSacados();
        this.vaciarMovimientos();
        super.nuevaPartida();
    }

    private void vaciarMovimientosSacados(){
        if (!this.movimientosSacados.isEmpty()){
            this.movimientosSacados.vacuum();
        }
    }
    private void vaciarMovimientos(){
        if (!this.movimientos.isEmpty()){
            this.movimientos.vacuum();
        }
    }

}
