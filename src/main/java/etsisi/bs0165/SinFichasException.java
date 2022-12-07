package etsisi.bs0165;

public class SinFichasException extends Exception{
    private static final String MENSAJE="EXCEPTION: ¡¡¡Te quedaste sin fichas!!!!";
    public SinFichasException(){
        super(MENSAJE);
    }
}
