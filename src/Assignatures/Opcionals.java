/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignatures;

import Cursos.Curs;

/**
 * @author Fede & Alberto
 */
public class Opcionals extends Assignatura {

    public static final int Teoria = 0, Practica = 1, noPerfil = -1;
    private int Perfil;

    public Opcionals(String nom, int codi, int Perfil, Curs curs) {
        super(nom, codi, curs);
        this.Perfil = (Perfil == Teoria || Perfil == Practica) ? Perfil : noPerfil;
    }

    public int getPerfil() {
        return Perfil;
    }
}
