package etsisi.juego;

/**
 * Exception que saltará si la columna es incorrecta.
 */
public class ColumnaIncorrectaException extends Exception{
    // ATRIBUTOS
    private static final String MENSAJE="EXCEPTION: Columna incorrecta\n\tIntroduzca valores validos";

    //CONSTRUCTOR
    ColumnaIncorrectaException (int min,int max){
        super(MENSAJE+" ["+min+"-"+max+"]");
    }
}
