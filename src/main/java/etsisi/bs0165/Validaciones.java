package etsisi.bs0165;

/**
 * Esta clase almacena las reglas del juego y comprueba si hay ganador.
 */
public class Validaciones {
    // ATRIBUTOS
    private final int INICIO_BUCLE=0;
    /**
     * Esta constante es el número de fichas que hay que tener en horizontal, vertical o diagonal para ganar
     */
    private final int NUM_FICHAS_GANADOR=4;
    private  final int numFilas;
    private final int numColumnas;
    private Tablero tablero;

    //CONSTRUCTOR
    public Validaciones(Tablero tablero){
        this.tablero=tablero;
        this.numColumnas = tablero.getNumColumnas();
        this.numFilas = tablero.getNumFilas();
    }

    // SETTERS

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    // METODOS

    /**
     * Este método se encarga de encontrar (si hay) ganador.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @param posicion Se pasa la posición de la ficha puesta para partir de dicha posición al tirar diagonales.
     * @return Devuelve "true" si hay ganador.
     */
    public boolean hayGanador(Ficha ficha, Coordenadas posicion){
        boolean hayGanador=false;
        if(checkFilas(ficha,posicion)){
            hayGanador= true;
        } else if (checkColumnas(ficha,posicion)) {
            hayGanador= true;
        } else if (checkDiagonales(ficha,posicion)) {
            hayGanador= true;
        }
        return hayGanador;
    }

    /**
     * Busca por filas si hay 4 fichas en horizontal.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkFilas (Ficha ficha, Coordenadas posicion){
        int i = posicion.getFila();
        boolean hayGanador=false;
        int j = INICIO_BUCLE;
        int contadorCasillas=0;
        while (j<numColumnas && !hayGanador){
            if(!(tablero.isEmpty(i,j))){
                if(tablero.getFichaPos(i,j).equals(ficha)){
                    contadorCasillas++;
                }else{
                    contadorCasillas=0;
                }
            }else{
                contadorCasillas=0;
            }
            if(contadorCasillas>=NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else {
                j++;
            }
        }
        return hayGanador;
    }

    /**
     * Busca por filas si hay 4 fichas en vertical.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkColumnas(Ficha ficha, Coordenadas posicion){
        int i=INICIO_BUCLE;
        int j= posicion.getColumna();
        boolean hayGanador=false;
        int contadorCasillas=0;
        while (i<numFilas && !hayGanador){
            if(!(tablero.isEmpty(i,j))){
                if(tablero.getFichaPos(i,j).equals(ficha)){
                    contadorCasillas++;
                }else{
                    contadorCasillas=0;
                }
            }else{
                contadorCasillas=0;
            }
            if(contadorCasillas>=NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else {
                i++;
            }
        }
        return hayGanador;
    }

    /**
     * Busca por filas si hay 4 fichas en diagonal.
     * @param ficha Se pasa una Ficha para buscar si esa ficha es ganadora.
     * @param posicion Se pasa la posición de la ficha puesta para partir de dicha posición al tirar diagonales.
     * @return Devuelve "true" si hay ganador.
     */
    private boolean checkDiagonales(Ficha ficha, Coordenadas posicion){
        boolean hayGanador=false;
        int diagonalIzquierdaArriba=diagonalIzquierdaArriba(ficha,posicion);
        int diagonalIzquierdaAbajo=diagonalIzquierdaAbajo(ficha,posicion);
        int diagonalDerechaArriba=diagonalDerechaArriba(ficha,posicion);
        int diagonalDerechaAbajo=diagonalDerechaAbajo(ficha,posicion);
        // Sumamos las dos grandes diagonales por si sumadas dan 4. Se le resta 1 porque la ficha se cuenta dos veces
        int diagonalGrande1=(diagonalIzquierdaArriba+diagonalDerechaAbajo)-1;
        int diagonalGrande2=(diagonalDerechaArriba+diagonalIzquierdaAbajo)-1;

        if((diagonalIzquierdaArriba==NUM_FICHAS_GANADOR) || (diagonalDerechaArriba==NUM_FICHAS_GANADOR) || (diagonalDerechaAbajo==NUM_FICHAS_GANADOR) || (diagonalIzquierdaAbajo==NUM_FICHAS_GANADOR) || (diagonalGrande1==NUM_FICHAS_GANADOR) || (diagonalGrande2==NUM_FICHAS_GANADOR)){
            hayGanador=true;
        }
        return hayGanador;
    }

    private int diagonalIzquierdaArriba(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(tablero.getFichaPos(fila,columna).equals(ficha)){
                contadorFichas++;
            }else{
                contadorFichas=0;
            }
            if(contadorFichas==NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else{
                fila--;
                columna--;
            }
        }
        return contadorFichas;
    }

    private int diagonalIzquierdaAbajo(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(tablero.getFichaPos(fila,columna).equals(ficha)){
                contadorFichas++;
            }else{
                contadorFichas=0;
            }
            if(contadorFichas==NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else{
                fila++;
                columna--;
            }
        }
        return contadorFichas;
    }

    private int diagonalDerechaAbajo(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(tablero.getFichaPos(fila,columna).equals(ficha)){
                contadorFichas++;
            }else{
                contadorFichas=0;
            }
            if(contadorFichas==NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else{
                fila++;
                columna++;
            }
        }
        return contadorFichas;
    }

    private int diagonalDerechaArriba(Ficha ficha, Coordenadas posicion){
        int fila= posicion.getFila();
        int columna= posicion.getColumna();
        boolean hayGanador= false;

        int contadorFichas=0;
        while(isInArray(fila,columna) && !hayGanador){
            if(tablero.getFichaPos(fila,columna).equals(ficha)){
                contadorFichas++;
            }else{
                contadorFichas=0;
            }
            if(contadorFichas==NUM_FICHAS_GANADOR){
                hayGanador=true;
            }else{
                fila--;
                columna++;
            }
        }
        return contadorFichas;
    }

    /**
     * Este método comprueba que los parámetros metidos están dentro del array.
     * @param fila  El indicador fila.
     * @param columna El indicador columna.
     * @return "True" si está dentro del array o "false" si está fuera.
     */
    private boolean isInArray(int fila, int columna){
        return (fila<numFilas && columna<numColumnas && fila>=0 && columna>=0);
    }
}
