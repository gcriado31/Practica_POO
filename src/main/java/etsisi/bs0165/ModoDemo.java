package etsisi.bs0165;

import java.awt.*;

/**
 * En esta clase se desarrollará en modo de juego Enfrentamiento.
 */
public class ModoDemo extends ModoJuego{
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
        do{
            while(!finJuego) {
                super.dibujar();
                int numJugador=super.turno.jugadorEnCurso +1;
                System.out.println("Turno de: " + super.turno.nombreJugadorConTurno()+" "+numJugador);
                Coordenadas posicion = null;
                try {
                    posicion = super.turno.tieneTurno().poner();
                } catch (SinFichasException ex) {
                    System.out.println(ex.getMessage());
                    finJuego = true;
                }finally {
                    if (posicion!=null) {
                        super.reglas.setTablero(tablero);
                        if (super.hayGanador(posicion)) {
                            System.out.println("Hay un ganador.");
                            finJuego = true;
                            this.ganador = super.turno.tieneTurno();
                            this.resultados();
                        } else if (super.tablero.tableroLleno()) {
                            System.out.println("El tablero se llenó.");
                            finJuego = true;
                            this.ganador = null;
                            this.resultados();
                        } else {
                            System.out.println("Como no hay un ganador y no se llenó el tablero continuamos.");
                            super.actualizaTablero(super.turno.tieneTurno().getTablero());
                            super.cambiaTurno();
                        }
                    }
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
    protected Jugador[] menuJugadores(Tablero tablero) {
        Jugador[] jugadors= new Jugador[super.NUMERO_JUGADORES];
        for (int i = 0; i < jugadors.length; i++) {
            jugadors[i]=new JugadorIA(super.fichaAzul,tablero);
            i++;
            if(i<jugadors.length) {
                jugadors[i] = new JugadorIA(super.fichaRoja, tablero);
            }
        }

        return jugadors;
    }


}
