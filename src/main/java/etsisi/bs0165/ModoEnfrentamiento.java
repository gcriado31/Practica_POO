package etsisi.bs0165;

import etsisi.pilas.DLCircularStack;

/**
 * En esta clase se desarrollará en modo de juego Demo.
 */
public class ModoEnfrentamiento extends ModoJuego{
    // ATRIBUTOS

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
                finJuego=super.ponerFicha();
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
        if(super.ganador!=null){
            System.out.println(super.GANADOR+super.ganador.getNombre()+" ¡ENHORABUENA!");
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
