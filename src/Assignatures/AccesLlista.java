/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignatures;

import Cursos.Curs;
import Exceptions.NoExistException;
import java.util.ArrayList;

/**
 * @author Fede & Alberto
 */
public interface AccesLlista {


    public  void nouObligatoria(String nom, int codi, int credit, Curs curs);

    public  void nouOpcional(String nom, int codi, int profile, Curs curs);

    public  Assignatura ExisteixAssig(String nom, int codi);

    public  void EliminarAssig(Assignatura s) throws NoExistException;

    public  String[] infoAssig(Assignatura s) throws NoExistException;

    public  ArrayList<String[]> infoTipus(Class<?> a) throws NoExistException;
    
    public  void nouFP(String nom, int codi, int tipus);

    public  void nouBatxiller(String nom, int codi, int any);

    public  Curs ExisteixCurs(String curs, int codi);

    public  void EliminarCurs(Curs c) throws NoExistException;

    public  ArrayList<String[]> infoCurs(Curs c) throws NoExistException;
}
