/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos;

/**
 * @author Fede & Alberto
 */
public class Batxiller extends Curs{

    public static final int Primer = 0, Segon = 1, noAny = -1;
    private int any;
    
    public Batxiller(String nom, int codi, int any) {
        super(nom, codi);
        this.any = (any == Primer || any == Segon)?  any : noAny;
    }

    public int getAny() {
        return any;
    }
}
