package etsisi.bs0165;
import java.awt.*;
import java.util.Scanner;

/*
    TODO:
        SOLUCIONAR SinFichasException
        HACER MAS EXCEPCIONES
        IMPLENTAR INTERFAZ/SUPERCLASE JUGADOR PARA JUGADORIA (TIENE QUE IMPLEMENTAR METODO ELEGIRMEJOR COLUMNA
        MIRAR TABLERO SI TIENE POSIBLE HERENCIA
        MODOS DE JUEGO ENTRENAMIENTO Y DEMO
        MODIFICAR METODO JUEGO PARA LOS MODOS
        CLASE VALIDACIONES
        INTENTAR CLASE MENÚS
 */

/**
 * Esta clase se encarga del juego
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
    private final String GANADOR="EL GANADOR ES ";
    private final Ficha fichaAzul=new Ficha('A',Color.BLUE);
    private final Ficha fichaRoja= new Ficha('R',Color.BLUE);

    // CONSTRUCTOR
    public Conecta4 (){
        this.jugadores=menuJugadores();
        this.turno=new Turno(jugadores);
        this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
        actualizaTableroEnJugadores(this.tablero);
    }

    // MÉTODOS

    /**
     * Dibuja el tablero.
     */
    public void dibujar(){
        tablero.dibujar();
    }

    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará una nueva partida.
     */
    public void jugar(){
        boolean finJuego=false;
        boolean finAplicacion=false;
        do {
            while (!finJuego) {
                dibujar();
                System.out.println("Turno de: " + turno.nombreJugadorConTurno());
                Coodenadas ficha=null;//turno.tieneTurno().poner();
                if (hayGanador(ficha)) {
                    finJuego = true;
                    this.ganador = turno.tieneTurno();
                    resultados();
                } else if (tablero.tableroLleno()) {
                    finJuego = true;
                    this.ganador = null;
                    resultados();
                } else {
                    actualizaTableroEnJugadores(turno.tieneTurno().getTablero());
                    turno.cambiaTurno();
                }
            }
            finAplicacion=fin();
            if(finAplicacion){
                nuevaPartida();
                finJuego=false;
            }
        }while (finAplicacion);
    }

    /**
     * Carga una nueva partida.
     */
    private void nuevaPartida(){
        this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
        actualizaTableroEnJugadores(tablero);
        System.out.println("\n----- NUEVA PARTIDA ------\n");
    }

    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida.
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false".
     */
    private boolean fin(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿DESEA VOLVER A JUGAR?(S/N)");
        char respuesta= input.nextLine().toUpperCase().charAt(0);
        return respuesta=='S';
    }

    /**
     * Da la bienvenida a los jugadores y almacena los jugadores.
     * @return Se devuelve el array de jugadores con toda la información.
     */
    private Jugador[] menuJugadores(){
        Jugador[] jugadors = new Jugador[NUMERO_JUGADORES];
        System.out.println(BIENVENIDA);
        for (int i = INICIO_BUCLE; i <NUMERO_JUGADORES; i++) {
            int pos=i+1;
            System.out.println("---JUGADOR "+pos+"---");
            if(i==0){
                jugadors[i]=new Jugador(infoJugador(),fichaAzul);
                System.out.println("Se le ha asignado la ficha azul");
            }else{
                jugadors[i]=new Jugador(infoJugador(), fichaRoja);
                System.out.println("Se le ha asignado la ficha azul");
            }
        }
        return jugadors;
    }

    /**
     * Pregunta la información al jugador.
     * @return Se devuelve un String con la información del juegador.
     */
    private String infoJugador(){
        Scanner input= new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        String nombre= input.nextLine();
        System.out.println();
        return nombre;
    }

    /**
     * Se recorre el array de jugadores actualizando el tablero.
     * @param tablero Se pasa el tablero que se quiere actualizar.
     */
    public void actualizaTableroEnJugadores(Tablero tablero){
        for(int i=INICIO_BUCLE;i<NUMERO_JUGADORES;i++){
            jugadores[i].setTablero(tablero);
        }
    }

    /**
     * Llama al método hayGanador de tablero para saber si hay ganador.
     * @return Devolverá "true" si hay ganador, si no "false".
     */
    public boolean hayGanador(Coodenadas posicion){
        return tablero.hayGanador(turno.tieneTurno().getFicha(), posicion);
    }

    public Jugador cambiarTurno(){
        turno.cambiaTurno();
        return turno.tieneTurno();
    }

    /**
     * Presenta los resultados
     */
    public void resultados(){
        if(ganador!=null){
            System.out.println(GANADOR+ganador.getNombre()+" ¡ENHORABUENA!");
        }else{
            System.out.println(EMPATE);
        }
        System.out.println("Tablero resultante ");
        dibujar();
    }

    public static void main(String[] args) {
        Conecta4 prueba=new Conecta4();
        prueba.jugar();
    }

}
