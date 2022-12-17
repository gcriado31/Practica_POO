package etsisi.bs0165;

public class ModoEnfrentamiento extends ModoJuego{
    // ATRIBUTOS
    private Turno turno;
    private Jugador [] jugadores;
    private Jugador ganador;
    private final int INICIO_BUCLE=0;

    //CONSTRUCTOR
    public ModoEnfrentamiento(Tablero tablero) {
        super(tablero);
        this.jugadores=this.menuJugadores();
        this.turno=new Turno(this.jugadores);
    }

    // MÉTODOS IMPLEMENTDADOS
    @Override
    protected void jugar() {
        boolean finJuego=false;
        boolean finModo;
        do {
            while (!finJuego) {
                this.dibujar();
                System.out.println("Turno de: " + this.turno.nombreJugadorConTurno());
                Coordenadas ficha = null ;
                try {
                    ficha=this.turno.tieneTurno().poner();
                }catch (SinFichasException ex){
                    System.out.println(ex.getMessage());
                    finJuego=true;
                }finally {
                    if (ficha!=null) {
                        this.reglas.setTablero(tablero);
                        if (this.hayGanador(ficha)) {
                            finJuego = true;
                            this.ganador = turno.tieneTurno();
                            this.resultados();
                        } else if (tablero.tableroLleno()) {
                            finJuego = true;
                            this.ganador = null;
                            this.resultados();
                        } else {
                            actualizaTablero(turno.tieneTurno().getTablero());
                            this.cambiaTurno();
                        }
                    }
                }

            }
            finModo=super.fin();
            if(finModo){
                nuevaPartida();
                finJuego=false;
            }
        }while (finModo);

    }

    @Override
    protected void cambiaTurno() {this.turno.cambiaTurno();}

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
    protected void actualizaTablero(Tablero tablero) {
        for(int i=INICIO_BUCLE;i<super.NUMERO_JUGADORES;i++) {
            this.jugadores[i].setTablero(tablero);
        }
    }

    @Override
    protected Jugador[] menuJugadores() {
        Jugador[] jugadors = new Jugador[super.NUMERO_JUGADORES];
        System.out.println(super.BIENVENIDA);
        for (int i = this.INICIO_BUCLE; i <super.NUMERO_JUGADORES; i++) {
            int pos=i+1;
            System.out.println("---JUGADOR "+pos+"---");
            if(i==0){
                jugadors[i]=new Jugador(super.infoJugador(),super.fichaAzul);
                System.out.println("Se le ha asignado la ficha azul");
            }else{
                jugadors[i]=new Jugador(super.infoJugador(),super.fichaRoja);
                System.out.println("Se le ha asignado la ficha roja");
            }
        }
        return jugadors;
    }

    @Override
    protected boolean hayGanador(Coordenadas coordenadas) {
        return super.reglas.hayGanador(turno.tieneTurno().getFicha(), coordenadas);
    }


}
