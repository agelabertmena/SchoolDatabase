/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignatures;

import Cursos.Batxiller;
import Cursos.Curs;
import Cursos.FP;
import Exceptions.NoExistException;
import Llista.Llista;
import Llista.Node;
import java.util.ArrayList;

/**
 * @author Fede & Alberto
 */
public class LlistaAssig {

    Llista<Assignatura> assignatures;

    public LlistaAssig() {
        assignatures = new Llista();
    }

    public Llista<Assignatura> getAssignatures() {
        return this.assignatures;
    }

    public void nouObligatoria(String nom, int codi, int credit, Curs curs) {
        this.assignatures.Afegir(new Obligatories(nom, codi, credit, curs));
    }

    public void nouOpcional(String nom, int codi, int perfil, Curs curs) {
        this.assignatures.Afegir(new Opcionals(nom, codi, perfil, curs));
    }

    public Assignatura ExisteixAssig(String nom, int codi) {
        Node<Assignatura> node = this.assignatures.getFirst();
        while (node != null) {
            if (node.getObjecte().getCodi() == codi || node.getObjecte().getNom().equalsIgnoreCase(nom)) {
                return node.getObjecte();
            }
            node = node.getNext();
        }
        return null;
    }

    public void EliminarAssig(Assignatura s) throws NoExistException {
        if (s != null) {
            this.assignatures.Eliminar(s);
        } else {
            throw new NoExistException("La asignatura no existe");
        }
    }

    public String[] infoAssig(Assignatura assig) throws NoExistException {
        String[] info = new String[8];
        if (assig != null) {
            info[0] = assig.getNom();
            info[1] = String.valueOf(assig.getCodi());
            if (assig.getClass() == Opcionals.class) {
                info[2] = "Optativa";
                switch (((Opcionals) assig).getPerfil()) {
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
            if (assig.getClass() == Obligatories.class) {
                info[2] = "Obligatoria";
                info[3] = String.valueOf(((Obligatories) assig).getCredit());
            }

            info[4] = assig.getCurs().getNom();
            info[5] = String.valueOf(assig.getCurs().getCodi());
            if (assig.getCurs().getClass() == FP.class) {
                info[6] = "FP";
                switch (((FP) assig.getCurs()).getTipus()) {
                    case FP.Electronica:
                        info[7] = "Electrónica";
                        break;
                    case FP.Informatica:
                        info[7] = "IT";
                        break;
                    case FP.Mecanica:
                        info[7] = "Mecánica";
                        break;
                    default:
                        info[7] = "No specialty";
                }
            }
            if (assig.getCurs().getClass() == Batxiller.class) {
                info[6] = "Bachiller";
                switch (((Batxiller) assig.getCurs()).getAny()) {
                    case Batxiller.Primer:
                        info[7] = "Primero";
                        break;
                    case Batxiller.Segon:
                        info[7] = "Segundo";
                        break;
                    default:
                        info[7] = "No Order";
                }
            }

            return info;
        } else {
            throw new NoExistException("La asignatura no existe");
        }
    }

    public ArrayList<String[]> infoTipus(Class<?> a) throws NoExistException {
        ArrayList<String[]> InfoSubject = new ArrayList();
        Node<Assignatura> ns = this.assignatures.getFirst();
        Curs ca = null;
        while (ns != null) {
            String[] info = {"", "", "", "", ""};
            if (ns.getObjecte().getClass() == a) {
                info[2] = ns.getObjecte().getNom();
                info[3] = String.valueOf(ns.getObjecte().getCodi());
                if (a == Opcionals.class) {
                    if (ca != ns.getObjecte().getCurs()) {
                        ca = ns.getObjecte().getCurs();
                        InfoSubject.add(new String[]{ns.getObjecte().getCurs().getNom(), "" + ns.getObjecte().getCurs().getCodi(), "", "", ""});
                    }
                    switch (((Opcionals) ns.getObjecte()).getPerfil()) {
                        case Opcionals.Practica:
                            info[4] = "Práctica";
                            break;
                        case Opcionals.Teoria:
                            info[4] = "Teoría";
                            break;
                        default:
                            info[4] = "No Profile";
                    }
                    InfoSubject.add(info);
                }
                if (a == Obligatories.class) {
                    if (ca != ns.getObjecte().getCurs()) {
                        ca = ns.getObjecte().getCurs();
                        InfoSubject.add(new String[]{ns.getObjecte().getCurs().getNom(), "" + ns.getObjecte().getCurs().getCodi(), "", "", ""});
                    }
                    info[4] = String.valueOf(((Obligatories) ns.getObjecte()).getCredit());
                    InfoSubject.add(info);
                }
            }
            ns = ns.getNext();
        }
        return InfoSubject;
    }
}
