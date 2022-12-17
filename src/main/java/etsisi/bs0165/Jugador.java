package etsisi.bs0165;



/**
 * Esta clase respresenta al jugador, pondrá ficha a través de Tablero y almacena nombre y ficha del jugador.
 */
public class Jugador {
    // ATRIBUTOS
    protected String nombre;
    protected int fichasRestantes;
    protected Ficha ficha;
    protected Tablero tablero;
    protected final String ERROR_TABLERO_LLENO="ERROR: TABLERO LLENO";
    private final int MAX_FICHAS=21;


    //CONSTRUCTORES
    public Jugador (String nombre, Ficha ficha){
        this.nombre=nombre;
        this.ficha=ficha;
        this.fichasRestantes=MAX_FICHAS;
    }

    /*public Jugador (String nombre, Ficha ficha, Tablero tablero){
        this.nombre=nombre;
        this.ficha=ficha;
        this.tablero=tablero;
        this.fichasRestantes=MAX_FICHAS;
    }*/

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
        return this.ficha;
    }
    public int getFichasRestantes() {
        return fichasRestantes;
    }

    public void setFichasRestantes(int fichasRestantes) {
        this.fichasRestantes = fichasRestantes;
    }

    //MÉTODOS

    /**
     * Método del jugador para poner su ficha.
     */
    public Coordenadas poner ()throws SinFichasException {
        if(!this.tablero.tableroLleno()){
            this.fichasRestantes--;
            return tablero.ponerFicha(ficha);
        }else if (this.fichasRestantes==0) {
            throw new SinFichasException(this.nombre);
        }else{  //SEGURAMENTE AQUÍ POSTERIORMENTE IRÁ UNA EXCEPCION "TableroLlenoException"
            System.out.println(ERROR_TABLERO_LLENO);
            return null;
        }
    }

}
