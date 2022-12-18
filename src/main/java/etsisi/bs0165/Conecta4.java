package etsisi.bs0165;
import java.awt.*;
import java.util.Scanner;

/*
    TODO
        HACER MAS EXCEPCIONES
        IMPLEMENTAR INTERFAZ/SUPERCLASE JUGADOR PARA JUGADORIA (TIENE QUE IMPLEMENTAR MÉTODO ELEGIR MEJOR COLUMNA)
        MODOS DE JUEGO ENTRENAMIENTO Y DEMO
        MODIFICAR MÉTODO JUEGO PARA LOS MODOS
        INTENTAR CLASE MENÚS (como superclase/interfaz que luego va a cada tipo de juego normal,demo,entrenamiento)


 */

/**
 * Esta clase se encarga del juego
 * @author Guillermo Criado
 * @version 3.0
 */
public class Conecta4 {
    // ATRIBUTOS
    private Tablero tablero;
    private final int NUM_FILAS=6;
    private final int NUM_COLUMNAS=7;

    private final String BIENVENIDA="Bienvenido a Conecta 4\nConsigue conectar 4 fichas en horizontal, vertical o en diagonal para ganar";
    private final String DESPEDIDA="Hasta luego. Esperamos volver a verle de nuevo.";
    private ModoEntrenamiento entrenamiento;
    private ModoEnfrentamiento enfrentamiento;
    private ModoDemo demo;

    // CONSTRUCTOR
    public Conecta4 (){
        this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
        this.entrenamiento=new ModoEntrenamiento(this.tablero);
        this.enfrentamiento=new ModoEnfrentamiento(this.tablero);
        this.demo=new ModoDemo(this.tablero);

    }

    // MÉTODOS

    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará.
     */
    public void jugar(){
        boolean finAplicacion;
        do {
            System.out.println(BIENVENIDA);
            finAplicacion=this.fin();
            if(finAplicacion){

            }
        }while (finAplicacion);
    }

    private char seleccionModo(){
        System.out.println("ELIJA UN MODO PARA JUGAR:"+
                            "\n\t1. MODO ENFRENTAMIENTO."+
                            "\n\t2. MODO ENTRENAMIENTO."+
                            "\n\t3. MODO DEMO."+
                            "\n\t0. S.");
        return '0';
    }

    /**
     * Este método será el fin del juego y dará la opcion de volver a jugar otra partida.
     * @return Si el jugador(es) quiere volver a jugar "true" si no "false".
     */
    private boolean fin(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿DESEA SALIR DE LA APLICACIÓN?(S/N)");
        char respuesta= input.nextLine().toUpperCase().charAt(0);
        return respuesta=='S';
    }

    public static void main(String[] args) {
        Conecta4 prueba=new Conecta4();
        prueba.jugar();
    }

}
