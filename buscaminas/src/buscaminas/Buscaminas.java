/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author Alex Recacha
 */
public class Buscaminas {

    public static void main(String[] args) {
        
        Tablero t = new Tablero(5, 5);
        t.insertarMinas(2);
        t.imprimirPrueba();
        
    }
    
}
