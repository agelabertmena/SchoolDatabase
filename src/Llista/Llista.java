package Llista;

/**
 * @author Fede & Alberto
 */

public class Llista<E> {

    private Node<E> first;

    public Llista() {
        this.first = null;
    }

    public Node<E> getFirst() {
        return first;
    }

    public void Afegir(E objecte) {
        Node<E> nouNode = new Node(objecte);
        String nom2, nom1 = objecte.toString();
        if (this.first == null) {
            this.first = nouNode;
        } else {
            Node<E> aux = this.first;
            nom2 = aux.getObjecte().toString();
            if (nom1.compareToIgnoreCase(nom2) < 0) {
                nouNode.setNextNode(aux);
                this.first = nouNode;
            } else {
                if (aux.getNext() == null) {
                    first.setNextNode(nouNode);
                } else {
                    Node<E> aux2 = aux.getNext();
                    nom2 = aux2.getObjecte().toString();
                    Boolean exit = false;
                    for (; !exit;) {
                        if (nom1.compareToIgnoreCase(nom2) < 0) {
                            nouNode.setNextNode(aux2);
                            aux.setNextNode(nouNode);
                            exit = true;
                        } else {
                            if (aux2.getNext() == null) {
                                aux2.setNextNode(nouNode);
                                exit = true;
                            } else {
                                aux = aux.getNext();
                                aux2 = aux2.getNext();
                                nom2 = aux2.getObjecte().toString();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean Eliminar(E objecte) {
        String nom = objecte.toString(), nom2;
        if (this.first == null) {
            return false;
        } else {
            Node<E> aux = this.first;
            nom2 = aux.getObjecte().toString();
            if (nom.equals(nom2)) {
                this.first = aux.getNext();
                return true;
            } else {
                if (aux.getNext() != null) {
                    Node<E> aux2 = aux.getNext();
                    nom2 = aux2.getObjecte().toString();
                    while (true) {
                        if (nom.equals(nom2)) {
                            aux.setNextNode(aux2.getNext());
                            return true;
                        } else {
                            if (aux2.getNext() != null) {
                                aux = aux.getNext();
                                aux2 = aux2.getNext();
                                nom2 = aux2.getObjecte().toString();
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
