package etsisi.bs0165;

import etsisi.pilas.DLCircularStack;

/**
 * En esta clase se desarrollará en modo de juego Demo.
 */
public class ModoEnfrentamiento extends ModoJuego{
    // ATRIBUTOS
    private Jugador ganador;
    private final int INICIO_BUCLE=0;

    //CONSTRUCTOR
    public ModoEnfrentamiento(Tablero tablero) {
        super(tablero);
    }

    // MÉTODOS IMPLEMENTDADOS
    @Override
    public void jugar() {
        boolean finJuego=false;
        boolean finModo;
        do {
            while (!finJuego) {
                super.dibujar();
                System.out.println("Turno de: " + super.turno.nombreJugadorConTurno());
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
                            this.ganador = super.turno.tieneTurno();
                            this.resultados();
                        } else if (super.tablero.tableroLleno()) {
                            finJuego = true;
                            this.ganador = null;
                            this.resultados();
                        } else {
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
        }while (finModo);

    }

    @Override
    protected void resultados() {
        if(this.ganador!=null){
            System.out.println(super.GANADOR+this.ganador.getNombre()+" ¡ENHORABUENA!");
        }else{
            System.out.println(super.EMPATE);
        }
        System.out.println("Tablero resultante ");
        super.dibujar();
    }

    @Override
    protected DLCircularStack<Jugador> menuJugadores(Tablero tablero) {
        DLCircularStack<Jugador> jugadors = new DLCircularStack<>();
        for (int i = this.INICIO_BUCLE; i <super.NUMERO_JUGADORES; i++) {
            int pos=i+1;
            System.out.println("---JUGADOR "+pos+"---");
            if(i==0){
                jugadors.push(new Jugador(super.infoJugador(),super.fichaAzul,tablero));
                System.out.println("Se le ha asignado la ficha azul");
            }else{
                jugadors.push(new Jugador(super.infoJugador(),super.fichaRoja,tablero));
                System.out.println("Se le ha asignado la ficha roja");
            }
        }
        return jugadors;
    }

}
