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
    }

    // MÉTODOS

    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará.
     */
    public void jugar(){
        boolean finAplicacion=false;
        System.out.println(BIENVENIDA);
        do {
            switch (this.seleccionModo()){
                case '1':
                    System.out.println("--- MODO ENFRENTAMIENTO ---");
                    this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
                    this.enfrentamiento=new ModoEnfrentamiento(this.tablero);
                    this.enfrentamiento.jugar();
                    break;
                case '2':
                    System.out.println("--- MODO ENTRENAMIENTO ---");
                    this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
                    this.entrenamiento=new ModoEntrenamiento(this.tablero);
                    this.entrenamiento.jugar();
                    break;
                case '3':
                    this.tablero=new Tablero(NUM_FILAS,NUM_COLUMNAS);
                    this.demo=new ModoDemo(this.tablero);
                    this.demo.jugar();
                    break;
                case'0':
                    finAplicacion=this.fin();
                    System.out.println(DESPEDIDA);
                    break;
                default:
                    System.out.println("TECLA INCORRECTA");
                    break;
            }
        }while (!finAplicacion);
    }

    private char seleccionModo(){
        System.out.println("ELIJA UN MODO PARA JUGAR:"+
                            "\n\t1. MODO ENFRENTAMIENTO."+
                            "\n\t2. MODO ENTRENAMIENTO."+
                            "\n\t3. MODO DEMO."+
                            "\n0. SALIR DE LA APLICACIÓN.");
        Scanner input=new Scanner(System.in);

        return input.nextLine().charAt(0);
    }

    /**
     * Este método será el fin de la aplicación y dará la opción de salir de ella.
     * @return Si el jugador(es) quiere salir de la aplicación "true" si no "false".
     */
    private boolean fin(){
        Scanner input= new Scanner(System.in);
        System.out.println("¿SEGURO QUE DESEA SALIR DE LA APLICACIÓN?(S/N)");
        char respuesta= input.nextLine().toUpperCase().charAt(0);
        return respuesta=='S';
    }

    public static void main(String[] args) {
        Conecta4 prueba=new Conecta4();
        prueba.jugar();
    }

}
