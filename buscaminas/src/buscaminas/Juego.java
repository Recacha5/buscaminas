/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

/**
 *
 * @author Alex Recacha
 */
public class Juego {

    private Tablero tablero;
    private int numMinas;
    private int numFilas;
    private int numColumnas;

    public Juego() {
        
    }

    /**
     * Configuración inicial
     */
    private void configurarJuego() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vamos a definir el tablero del juego");
        System.out.println("Dime el numero de filas:");
        numFilas = sc.nextInt();
        System.out.println("Dime el numero de columnas:");
        numColumnas = sc.nextInt();
        System.out.println("Ahora dime con cuántas minas quieres jugar");
        numMinas = sc.nextInt();
    }

    /**
     * crea el tablero
     */
    public void iniciarJuego() {

        tablero = new Tablero(numFilas, numColumnas);

    }

    /**
     * comenzar el juego
     */
    public void jugar() {
        Scanner sc = new Scanner(System.in);
        int fila, columna;
        System.out.println("Vamos a comenzar el buscaminas...");
        configurarJuego();
        iniciarJuego();
        while (!partidaGanada()) {            
            mostrarTablero();
            System.out.println("Dime la fila:");
            fila = sc.nextInt();
            System.out.println("Dime la columna:");
            columna = sc.nextInt();
            if (elegirOperacion() == 1) {
                descubrirCasilla(fila, columna);
            }
        }
        
    }

    /**
     * muestra el tablero
     */
    private void mostrarTablero() {

        System.out.print(" ");
        for (int i = 0; i < tablero.getTabla().length; i++) {
            System.out.print("  " + i);
        }
        System.out.println("");
        for (int i = 0; i < tablero.getTabla().length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero.getTabla().length; j++) {
                
                if (tablero.getTabla()[i][j].isVisible()) {
                    if (tablero.getTabla()[i][j].isMina()) {
                        System.out.print(" M ");
                    }else if(tablero.getTabla()[i][j].isBandera()){
                        System.out.print(" B ");
                    }else if (tablero.getTabla()[i][j].isBlanco()){
                        System.out.print("   ");
                    }
                } else{
                    System.out.print(" . ");
                }
            }
            System.out.println(" " + i);
        }
        System.out.print(" ");
        for (int i = 0; i < tablero.getTabla().length; i++) {

            System.out.print("  " + i);
        }
        System.out.println("");
    }

    /**
     * 
     * @return devuelve la seleccion (1, 2, 3)
     * 
     */
    private int elegirOperacion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige qué quieres hacer:");
        System.out.println("1- Descubrir.");
        System.out.println("2- Poner bandera.");
        System.out.println("3- Quitar bandera.");
        return sc.nextInt();
    }

    /**
     * 
     * @param fila recibe la fila
     * @param columna recibe la columna
     * @return devuelve true si las coordenadas introducidas son correctas, devuelve false si no lo son.
     */
    private boolean coordenadasCorrectas(int fila, int columna) {

        if (fila >= 0 && fila < numFilas && columna >= 0 & columna < numColumnas) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * termina el juego porque has pisado una mina
     */
    private void acabarJuegoMina() {

        System.out.println("Juego finalizado, has pisado una mina");
        tablero.imprimirPrueba();

    }

    /**
     * @param fila recibe la fila
     * @param columna recibe la columna
     * @return devuelve true si la casilla no es mina, devuelve false si has pisado una mina
     */
    private boolean descubrirCasilla(int fila, int columna) {
        coordenadasCorrectas(fila, columna);
        if (!tablero.getTabla()[fila][columna].isMina()) {
            tablero.getTabla()[fila][columna].setVisible(true);
            descubrirBlanco(fila, columna);
            return true;
        } else {
            acabarJuegoMina();
            return false;
        }
    }
    /**
     * 
     * @param fila recibe la fila
     * @param columna recibe la columna
     * descubre la casilla seleccionada
     */
    private void descubrirBlanco(int fila, int columna){
        
        for (int i = fila-1; i <= fila+1; i++) {
            for (int j = columna-1; j <= columna+1; j++) {
                if (i>=0 && i< numFilas && j>=0 && j< numColumnas && !tablero.getTabla()[i][j].isMina() && tablero.getTabla()[i][j].getNumero() == 0) {
                    tablero.getTabla()[fila][columna].setVisible(true);
                }
            }
        }
    }
    /**
     * 
     * @return devuelve true si no has pisado ninguna mina y has descubierto todas las casillas, devuelve falso si aun sigue la partida
     */
    private boolean partidaGanada(){
        int aux = 0;
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (!tablero.getTabla()[i][j].isVisible()) {
                    aux++;
                }
            }
        }
        if (aux == 0) {
            return true;
        }else{
            return false;
        }
    }
    
}
