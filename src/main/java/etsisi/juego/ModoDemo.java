package etsisi.juego;

import java.awt.*;

import etsisi.pilas.*;
/**
 * En esta clase se desarrollará en modo de juego Enfrentamiento.
 */
public class ModoDemo extends ModoJuego {
    // ATRIBUTOS
    private final String BIENVENIDA_MODODEMO="--- BIENVENIDO AL MODO DE JUEGO DEMO ---\n En este modo verá un tutorial de cómo funciona el juego.";


    // CONSTRUCTOR
    public ModoDemo (){
        super();
    }

    // MÉTODOS
    @Override
    protected void jugar() {
        Viewer.printString(BIENVENIDA_MODODEMO);
        boolean finJuego=false;
        boolean finModo;
        super.dibujar();
        do{
            while(!finJuego) {
                finJuego = super.ponerFicha();
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
            Viewer.printString((super.GANADOR+this.ganador.getNombre()+" Con color"+color+". Ya que encajó las 4 fichas."));
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
            jugadors.push(new JugadorIA(super.fichaAzul,super.tablero));
            i++;
            if(i<super.NUMERO_JUGADORES) {
                jugadors.push(new JugadorIA(super.fichaRoja, super.tablero));
            }
        }

        return jugadors;
    }


}
