package etsisi.bs0165;

/**
 * Almacena la ficha y crea la forma de representación de los bordes laterales
 */
public class Casilla {
    //ATRIBUTOS
    private int fila;
    private int columna;
    private Ficha ficha;
    private final String CASILLA_VACIA="  ";

    //CONSTRUCTORES
    public Casilla(int fila,int columna){
        this.fila=fila;
        this.columna=columna;
        this.ficha=new Ficha();
    }
    public Casilla (int fila, int columna, Ficha ficha){
        this.fila=fila;
        this.columna=columna;
        this.ficha=ficha;
    }

    //SETTERS Y GETTERS
    public Ficha getFicha() {
        return ficha;
    }
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    //MÉTODOS
    public String dibujar(){
        if (isEmpty()){
            return "|"+CASILLA_VACIA+"|";
        }else{
            return "|"+ficha.mostrar()+"|";
        }
    }
    public boolean isEmpty(){
        return this.ficha.getContenido()==' ';
    }

}
