package etsisi.bs0165;

public class Casilla {
    //ATRIBUTOS
    private int fila;
    private int columna;
    private Ficha ficha;
    private static final String CASILLA_VACIA="\t";

    //CONSTRUCTORES
    public Casilla(int fila,int columna){
        this.fila=fila;
        this.columna=columna;
        this.ficha=null;
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
        return this.ficha==null;
    }

}
