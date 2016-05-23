package Assignatures;

import Cursos.Curs;

/**
 * @author Fede & Alberto
 */

public class Assignatura implements AccesElement {

    private Curs curs;
    private String nom;
    private int codi;

    public Assignatura(String nom, int codi, Curs curs) {
        this.nom = nom;
        this.codi = codi;
        this.curs = curs;
    }

    public Curs getCurs() {
        return curs;
    }

    public String getNom() {
        return nom;
    }

    public int getCodi() {
        return codi;
    }
    
    @Override
    public String toString() {
        return this.nom;
    }
}
