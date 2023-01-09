package etsisi.juego;
import java.util.Scanner;


/**
 * Esta clase se encarga del juego
 * @author Guillermo Criado
 * @version 4.2
 */
public class Conecta4 {
    // ATRIBUTOS


    private final String BIENVENIDA="Bienvenido a Conecta 4\nConsigue conectar 4 fichas en horizontal, vertical o en diagonal para ganar";
    private final String DESPEDIDA="Hasta luego. Esperamos volver a verle de nuevo.";
    private final String MODO_ENFRENTAMIENTO="--- MODO ENFRENTAMIENTO ---";
    private final String MODO_ENTRENAMIENTO="--- MODO ENTRENAMIENTO ---";
    private final String SALIR="¿SEGURO QUE DESEA SALIR DE LA APLICACIÓN?(S/N)";
    private final String TECLA_INCOORECTA="TECLA INCORRECTA";
    private final String OPCIONES="ELIJA UN MODO PARA JUGAR:"+
                                    "\n\t1. MODO ENFRENTAMIENTO."+
                                    "\n\t2. MODO ENTRENAMIENTO."+
                                    "\n\t3. MODO DEMO."+
                                    "\n0. SALIR DE LA APLICACIÓN.";
    private ModoEntrenamiento entrenamiento;
    private ModoEnfrentamiento enfrentamiento;
    private ModoDemo demo;

    // CONSTRUCTOR
    public Conecta4 (){
    }

    // MÉTODOS

    /**
     * Ejecuta el juego y mientras el jugador(es) quiera(n) se ejecutará.
     */
    public void jugar(){
        boolean finAplicacion=false;
        Viewer.printString(BIENVENIDA);
        do {
            switch (this.seleccionModo()){
                case '1':
                    Viewer.printString(MODO_ENFRENTAMIENTO);
                    this.enfrentamiento=new ModoEnfrentamiento();
                    this.enfrentamiento.jugar();
                    break;
                case '2':
                    Viewer.printString(MODO_ENTRENAMIENTO);
                    this.entrenamiento=new ModoEntrenamiento();
                    this.entrenamiento.jugar();
                    break;
                case '3':
                    this.demo=new ModoDemo();
                    this.demo.jugar();
                    break;
                case'0':
                    finAplicacion=this.fin();
                    break;
                default:
                    Viewer.printString(TECLA_INCOORECTA);
                    break;
            }
        }while (!finAplicacion);
        Viewer.printString(DESPEDIDA);
    }

    private char seleccionModo(){
        return Viewer.options(OPCIONES);
    }

    /**
     * Este método será el fin de la aplicación y dará la opción de salir de ella.
     * @return Si el jugador(es) quiere salir de la aplicación "true" si no "false".
     */
    private boolean fin(){
        char respuesta= Viewer.options(SALIR);
        return respuesta=='S';
    }

    public static void main(String[] args) {
        Conecta4 prueba=new Conecta4();
        prueba.jugar();
    }

}
