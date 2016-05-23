/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignatures;

import Cursos.Curs;

/**
 * @author Fede & Alberto
 */
public class Obligatories extends Assignatura {

    private int credit;

    public Obligatories(String nom, int codi, int credit, Curs curs) {
        super(nom, codi, curs);
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }
}
