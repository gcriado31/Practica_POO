package etsisi.bs0165;

/**
 * En esta clase se desarrollará en modo de juego Entrenamiento.
 */
public class ModoEntrenamiento extends ModoJuego{
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
            while(!finJuego) {
                super.dibujar();
                System.out.println("Turno de: " + super.turno.nombreJugadorConTurno());
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
    protected Jugador[] menuJugadores(Tablero tablero) {
        Jugador[] jugadors= new Jugador[super.NUMERO_JUGADORES];
        for (int i = 0; i < jugadors.length; i++) {
            int pos=i+1;
            System.out.println("---JUGADOR "+pos+"---");
            jugadors[i]=new Jugador(super.infoJugador(),super.fichaAzul,tablero);
            i++; // Aquí sumamos uno para poder altenar en un ciclo del bucle.
            if(i<jugadors.length) {
                jugadors[i] = new JugadorIA(super.fichaRoja, tablero);
            }
        }

        return jugadors;
    }

    public static void main(String[] args) {
        ModoEntrenamiento modo= new ModoEntrenamiento(new Tablero(6,7));
        modo.jugar();
    }

}
