/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos;

/**
 * @author Fede & Alberto
 */
public class FP extends Curs {

    private int tipus;
    public static final int Informatica = 0, Mecanica = 1, Electronica = 2, noTipus = -1;

    public FP(String nom, int codi, int tipus) {
        super(nom, codi);
        this.tipus = (tipus >= FP.Informatica && tipus <= FP.Electronica) ? tipus : noTipus;
    }

    public int getTipus() {
        return tipus;
    }
}
