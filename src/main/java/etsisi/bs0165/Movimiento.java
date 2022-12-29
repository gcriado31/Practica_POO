package etsisi.bs0165;

/**
 * Esta clase almacena el tablero anterior al movimiento y las coordenadas donde se ha puesto la ficha.
 * Nos ayudará en el patrón undo-redo.
 */
public class Movimiento {
    // ATRIBUTOS
    private Tablero tableroAnterior;
    private Coordenadas fichaIntroducida;

    // CONSTRUCTOR
    public Movimiento(Tablero tablero, Coordenadas fichaIntroducida){
        this.tableroAnterior=tablero;
        this.fichaIntroducida=fichaIntroducida;
    }

    // GETTERS Y SETTERS
    public Tablero getTablero() {
        return tableroAnterior;
    }

    public void setTablero(Tablero tablero) {
        this.tableroAnterior = tablero;
    }

    public Coordenadas getFichaIntroducida() {
        return fichaIntroducida;
    }

    public void setFichaIntroducida(Coordenadas fichaIntroducida) {
        this.fichaIntroducida = fichaIntroducida;
    }

}
