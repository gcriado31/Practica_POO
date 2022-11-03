package etsisi.bs0165;


/**
 * Esta clase respresenta al jugador, pondrá ficha a través de Tablero y almacena nombre y ficha del jugador.
 */
public class Jugador {
    // ATRIBUTOS
    private String nombre;
    private int fichasRestantes;
    private Ficha ficha;
    private Tablero tablero;
    private final String ERROR_TABLERO_LLENO="ERROR: TABLERO LLENO";
    private final int MAX_FICHAS=21;


    //CONSTRUCTORES
    public Jugador (String nombre, Ficha ficha){
        this.nombre=nombre;
        this.ficha=ficha;
        this.fichasRestantes=MAX_FICHAS;
    }

    public Jugador (String nombre, Ficha ficha, Tablero tablero){
        this.nombre=nombre;
        this.ficha=ficha;
        this.tablero=tablero;
        this.fichasRestantes=MAX_FICHAS;
    }

    //SETTERS Y GETTERS
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getNombre() {
        return nombre;
    }

    public Ficha getFicha() {
        return ficha;
    }
    public int getFichasRestantes() {
        return fichasRestantes;
    }

    //MÉTODOS

    /**
     * Método del jugador para poner su ficha.
     */
    public void poner (){
        if(!tablero.tableroLleno()){
            tablero.ponerFicha(ficha);
        }else{  //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "TableroLlenoException"
            System.out.println(ERROR_TABLERO_LLENO);
        }
    }

}
