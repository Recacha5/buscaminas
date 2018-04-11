/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Random;

/**
 *
 * @author Alex Recacha
 */
public class Tablero {

    private int numFilas;
    private int numColumnas;
    private int numMinas;
    private Casilla[][] tabla;

    public Tablero(int numFilas, int numColumnas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        tabla = new Casilla[numFilas][numColumnas];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                tabla[i][j] = new Casilla();
            }
        }
    }

    public void insertarMinas(int minas) {

        Random r = new Random();
        for (int i = 0; i < minas; i++) {
            int fila = r.nextInt();
            int columna = r.nextInt();
            if (tabla[fila][columna] != null && !tabla[fila][columna].isMina()) {
                tabla[fila][columna].setMina(true);
            } else {
                i--;
            }
        }
    }

    public void imprimirPrueba() {
        System.out.print(" ");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("  " + i);
        }
        System.out.println("");
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabla[i].length; j++) {
                
                if (tabla[i][j].isMina()) {
                    System.out.print(" M ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println(" " + i);
        }
        System.out.print(" ");
        for (int i = 0; i < tabla.length; i++) {
            
            System.out.print("  " + i);
        }
        System.out.println("");
    }

    public int calculaNumMinasCasilla(int fila, int columna) {

        return 0;
    }

    /*public Casilla getCasilla(int fila, int columna) {

    }*/

    public void calcularTablero() {

    }

}
