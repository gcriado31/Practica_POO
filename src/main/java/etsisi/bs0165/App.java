package etsisi.bs0165;


import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        boolean prueba=false;
        hola(prueba);
        System.out.println(prueba);

    }

    static void hola(boolean prueba){

        int i=0;
        do{
            i++;
            if ((i+1)%3==0){
                prueba=true;
            }
        }while (i<20 && !prueba);
        System.out.println("Dentro del método-> "+prueba);
    }

}
