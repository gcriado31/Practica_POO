package etsisi.bs0165;

import etsisi.pilas.DLStack;

import java.util.Scanner;

public abstract class ModoJuegoIA extends ModoJuego{
    // ATRIBUTOS
    protected DLStack<Movimiento> movimientos;
    protected DLStack<Movimiento> movimientosSacados;

    // CONSTRUCTOR
    protected ModoJuegoIA(Tablero tablero) {
        super(tablero);
    }

    //MÉTODOS ABSTACTOS

    /**
     * Método para el patrón undo.
     */
    protected abstract void undo();

    /**
     * Método para el patrón redo.
     */
    protected abstract void redo();

    //MÉTODOS DE LA CLASE
    /**
     * Método para que el usuario elija qué hacer.
     * @return Devuelve la elección del usuario.
     */
    protected char eleccionMovimiento(){
        System.out.println("Eliga que hacer:\n" +
                "\t1. Poner ficha.\n" +
                "\t2. Deshacer movimiento.");
        if(!movimientosSacados.isEmpty()){
            System.out.println("\t3. Rehacer movimiento");
        }
        Scanner input=new Scanner(System.in);
        return input.next().charAt(0);
    }

    protected void opciones(){
        switch (eleccionMovimiento()){
            case'1':
            case '2':
                undo();
            case'3':
                redo();
        }
    }

}
