/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cursos;

import Exceptions.NoExistException;
import Llista.Llista;
import Llista.Node;
import Assignatures.Obligatories;
import Assignatures.Opcionals;
import Assignatures.Assignatura;
import java.util.ArrayList;
import Assignatures.AccesLlista;

/**
 * @author Fede & Alberto
 */
public class LlistaCurs implements AccesLlista {
    
    private Llista<Curs> Cursos;
    
    public LlistaCurs(){
        Cursos = new Llista();
    }

    public Llista<Curs> getCursos() {
        return Cursos;
    }

    public void nouFP(String nom, int codi, int tipus) {
        Cursos.Afegir(new FP(nom, codi, tipus));
    }

    public void nouBatxiller(String nom, int codi, int any) {
        Cursos.Afegir(new Batxiller(nom, codi, any));
    }

    public Curs ExisteixCurs(String curs, int codi) {
        Node<Curs> c = Cursos.getFirst();
        while (c != null) {
            if (c.getObjecte().getNom().equalsIgnoreCase(curs) || c.getObjecte().getCodi() == codi) {
                return c.getObjecte();
            } else {
                c = c.getNext();
            }
        }
        return null;
    }

   
    public void EliminarCurs(Curs c) throws NoExistException {
        if (c != null) {
            Cursos.Eliminar(c); // borramos el curso
        } else {
            throw new NoExistException("El curso no existe");
        }
    }

    public ArrayList<String[]> infoCurs(Curs c) throws NoExistException {
        if (c != null) {
            ArrayList<String[]> InfoCourse = new ArrayList();
            String[] info = new String[4];
            info[0] = c.getNom();
            info[1] = String.valueOf(c.getCodi());
            if (c.getClass() == FP.class) {
                info[2] = "FP";
                switch (((FP) c).getTipus()) {
                    case FP.Electronica:
                        info[3] = "Electrónica";
                        break;
                    case FP.Informatica:
                        info[3] = "IT";
                        break;
                    case FP.Mecanica:
                        info[3] = "Mecánica";
                        break;
                    default:
                        info[3] = "No specialty";
                }
            }
            if (c.getClass() == Batxiller.class) {
                info[2] = "Bachiller";
                switch (((Batxiller) c).getAny()) {
                    case Batxiller.Primer:
                        info[3] = "Primero";
                        break;
                    case Batxiller.Segon:
                        info[3] = "Segundo";
                        break;
                    default:
                        info[3] = "No Order";
                }
            }
            InfoCourse.add(info);
            Node<Assignatura> subjects = c.getLlistaAssig().getAssignatures().getFirst();
            while (subjects != null) {
                info = new String[4];
                info[0] = subjects.getObjecte().getNom();
                info[1] = String.valueOf(subjects.getObjecte().getCodi());
                if (subjects.getObjecte().getClass() == Opcionals.class) {
                    info[2] = "Optativa";
                    switch (((Opcionals) subjects.getObjecte()).getPerfil()) {
                        case Opcionals.Practica:
                            info[3] = "Práctica";
                            break;
                        case Opcionals.Teoria:
                            info[3] = "Teoría";
                            break;
                        default:
                            info[3] = "No Profile";
                    }
                }
                if (subjects.getObjecte().getClass() == Obligatories.class) {
                    info[2] = "Obligatoria";
                    info[3] = String.valueOf(((Obligatories) subjects.getObjecte()).getCredit());
                }
                InfoCourse.add(info);
                subjects = subjects.getNext();
            }
            return InfoCourse;
        } else {
            throw new NoExistException("No existe el curso");
        }
    }

    @Override
    public void nouObligatoria(String nom, int codi, int credit, Curs curs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nouOpcional(String nom, int codi, int profile, Curs curs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Assignatura ExisteixAssig(String nom, int codi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EliminarAssig(Assignatura s) throws NoExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] infoAssig(Assignatura s) throws NoExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String[]> infoTipus(Class<?> a) throws NoExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
