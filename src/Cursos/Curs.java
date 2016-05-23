package Cursos;

import Assignatures.LlistaAssig;
import Assignatures.AccesElement;

/**
 * @author Fede & Alberto
 */

public class Curs implements AccesElement{
    
    private LlistaAssig LAssig;
    private String nom;
    private int codi;

    public Curs(String nom, int codi) {
        this.nom = nom;
        this.codi = codi;
        LAssig = new LlistaAssig();
    }

    public LlistaAssig getLlistaAssig() {
        return LAssig;
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

    @Override
    public Curs getCurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
