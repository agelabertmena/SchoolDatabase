/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignatures;

import Cursos.Curs;

/**
 *
 * @author Fede & Alberto
 */
public interface AccesElement {
    
    public Curs getCurs();

    public String getNom();

    public int getCodi();
    
    @Override
    public String toString();
    
    
}
