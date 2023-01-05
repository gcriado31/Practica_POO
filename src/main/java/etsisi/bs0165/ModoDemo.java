package etsisi.bs0165;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Scanner;

import etsisi.pilas.*;
/**
 * En esta clase se desarrollará en modo de juego Enfrentamiento.
 */
public class ModoDemo extends ModoJuegoIA{
    // ATRIBUTOS
    private Jugador ganador;

    // CONSTRUCTOR
    public ModoDemo (Tablero tablero){
        super(tablero);
    }



    // MÉTODOS
    @Override
    protected void jugar() {
        System.out.println("--- BIENVENIDO AL MODO DE JUEGO DEMO ---\n En este modo verá un tutorial de cómo funciona el juego.");
        boolean finJuego=false;
        boolean finModo;
        int contadorMovimientos=0;
        super.dibujar();
        do{
            while(!finJuego) {
                if(contadorMovimientos%2!=0) {
                    if (this.pararSimulacion()) {
                        finJuego = super.opciones();
                    } else {
                        finJuego = super.ponerFicha();
                    }
                }
                contadorMovimientos++;
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
            String color;
            if(this.ganador.getFicha().getColor().equals(Color.BLUE)){
                color=" azul ";
            }else{
                color=" rojo ";
            }
            System.out.println(super.GANADOR+this.ganador.getNombre()+" Con color"+color+". Ya que encajó las 4 fichas.");
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
            jugadors.push(new JugadorIA(super.fichaAzul,tablero));
            i++;
            if(i<super.NUMERO_JUGADORES) {
                jugadors.push(new JugadorIA(super.fichaRoja, tablero));
            }
        }

        return jugadors;
    }

    private boolean pararSimulacion(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿Desea parar la simulación?(S/N)");
        char eleccion=input.next().toUpperCase().charAt(0);
        return eleccion=='S';
    }

    @Override
    protected void undo() {
        // Deshacemos el movimiento de la IA que tenga turno
        try {
            super.movimientosSacados.push(super.movimientos.pop());
            System.out.println("Se ha retrocedido un movimiento");
            super.actualizaTablero(super.movimientos.top()); // Actualizamos el tablero al tablero anterior
        } catch (StackEmptyException e) {
            System.out.println("No se puede retroceder más");
        }
    }

    @Override
    protected void redo() {
        // Rehacemos el movimiento.
        try {
            super.movimientos.push(super.movimientosSacados.pop());
            System.out.println("Se ha retrocedido un movimiento");
            super.actualizaTablero(super.movimientos.top()); // Actualizamos el tablero al tablero anterior
        } catch (StackEmptyException e) {
            System.out.println("No se puede retroceder más");
        }
    }

}
