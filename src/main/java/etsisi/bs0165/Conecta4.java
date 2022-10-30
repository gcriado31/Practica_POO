package etsisi.bs0165;

/*
*   Falta:
*      - CREAR UN ACTUALIZA TABLERO EN CALSE TURNO
*      - ELIMINA ARRAY JUGADORES PARA JUGAR CON TURNO
*
*/

import java.awt.*;
import java.util.Scanner;

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
    public void dibujar(){
        tablero.dibujar();
    }

    public void jugar(){
        boolean finJuego=false;
        boolean finAplicacion=false;
        do {
            while (!finJuego) {
                dibujar();
                System.out.println("Turno de: " + turno.nombreJugadorConTurno());
                turno.tieneTurno().poner();
                if (hayGanador()) {
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
                this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
                actualizaTableroEnJugadores(tablero);
                finJuego=false;
                System.out.println("\n----- NUEVA PARTIDA ------\n");
            }
        }while (finAplicacion);
    }

    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false"
     */
    private boolean fin(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿DESEA VOLVER A JUGAR?(S/N)");
        char respuesta= input.nextLine().toUpperCase().charAt(0);
        return respuesta=='S';
    }

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

    private String infoJugador(){
        Scanner input= new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        String nombre= input.nextLine();
        System.out.println();
        return nombre;
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
