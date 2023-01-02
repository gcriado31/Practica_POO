package etsisi.bs0165;

/**
 * Esta clase almacena el tablero y las coordenadas donde se ha puesto la ficha.
 * Nos ayudará en el patrón undo-redo.
 */
public class Movimiento {
    // ATRIBUTOS
    private Tablero tablero;
    private Coordenadas fichaIntroducida;

    // CONSTRUCTOR
    public Movimiento(Tablero tablero, Coordenadas fichaIntroducida){
        this.tablero =tablero;
        this.fichaIntroducida=fichaIntroducida;
    }

    // GETTERS Y SETTERS
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Coordenadas getFichaIntroducida() {
        return fichaIntroducida;
    }

    public void setFichaIntroducida(Coordenadas fichaIntroducida) {
        this.fichaIntroducida = fichaIntroducida;
    }

}
