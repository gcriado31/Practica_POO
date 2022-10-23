package etsisi.bs0165;

/*
*   Falta:
*      - CREAR UN ACTUALIZA TABLERO EN CALSE TURNO
*      - ELIMINA ARRAY JUGADORES PARA JUGAR CON TURNO
*      - CREAR CONSTANTE INICIO BUCLES TODAS LAS CLASES
*
*/

public class Conecta4 {
    // ATRIBUTOS
    private Turno turno;
    private Jugador [] jugadores;
    private Jugador ganador;
    private Tablero tablero;
    private final int NUMERO_JUGADORES=2;
    private final int INICIO_BUCLE=0;
    private final int NUM_FILAS=6;
    private final int NUM_COLUMNAS=7;
    private final String BIENVENIDA="Bienvenido a Conecta 4\nConsigue conectar 4 fichas en linea o en diagonal para ganar";
    private final String EMPATE="¡¡¡EMPATE!!!";
    private final String GANADOR="EL GANADOR ES "+ganador.getNombre()+" ¡ENHORABUENA!";

    // CONSTRUCTOR
    public Conecta4 (Jugador[] jugadores){
        this.jugadores=jugadores;
        this.turno=new Turno(jugadores);
        this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
    }

    // MÉTODOS
    public void dibujar(){
        tablero.dibujar();
    }

    public void jugar(){

    }

    public void actualizaTableroEnJugadores(Tablero tablero){
        for(int i=INICIO_BUCLE;i<NUMERO_JUGADORES;i++){
            jugadores[i].setTablero(tablero);
        }
    }

    public boolean hayGanador(){
        return tablero.hayGanador(turno.tieneTurno().getFicha());
    }

    public Jugador cambiarTurno(){
        turno.cambiaTurno();
        return turno.tieneTurno();
    }

    public void resultados(){
        if(hayGanador()){

        }
    }

}
