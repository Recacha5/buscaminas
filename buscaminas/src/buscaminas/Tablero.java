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
            int fila = r.nextInt(numFilas);
            int columna = r.nextInt(numColumnas);
            if (!tabla[fila][columna].isMina()) {
                getTabla()[fila][columna].setMina(true);
            } else {
                i--;
            }
        }
    }

    public void imprimirPrueba() {
        System.out.print(" ");
        for (int i = 0; i < getTabla().length; i++) {
            System.out.print("  " + i);
        }
        System.out.println("");
        for (int i = 0; i < getTabla().length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < getTabla()[i].length; j++) {
                
                if (getTabla()[i][j].isMina()) {
                    System.out.print(" M ");
                } else if (tabla[i][j].getNumero() != 0) {
                    System.out.print(" "+tabla[i][j].getNumero()+" ");
                }else {
                    System.out.print("   ");
                }
            }
            System.out.println(" " + i);
        }
        System.out.print(" ");
        for (int i = 0; i < getTabla().length; i++) {
            
            System.out.print("  " + i);
        }
        System.out.println("");
    }

    
    public int calculaNumMinasCasilla(int fila, int columna) {
        
        int aux = 0;
        
        for (int i = fila-1; i <= fila+1; i++) {
            for (int j = columna-1; j <= columna+1; j++) {
                if (i>=0 && i< numFilas && j>=0 && j< numColumnas && getTabla()[i][j].isMina()) {
                    aux++;
                }
            }
        }
        
        return aux;
    }

    public Casilla getCasilla(int fila, int columna) {

        return tabla[fila][columna];
        
    }

    public void calcularTablero() {
        
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                
                if (!tabla[i][j].isMina()) {
                    int num = calculaNumMinasCasilla(i, j);
                    tabla[i][j].setNumero(num);
                }
            }
        }
        
    }

    /**
     * @return the tabla
     */
    public Casilla[][] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(Casilla[][] tabla) {
        this.tabla = tabla;
    }

}
