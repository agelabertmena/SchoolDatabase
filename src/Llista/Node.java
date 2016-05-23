package Llista;

/**
 * @author Fede & Alberto
 */

public class Node<E> {

    private E objecte;
    private Node<E> next;
        
    public Node(E object) {
        this.objecte = object;
        this.next = null;
    }

    public void setNextNode(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public E getObjecte() {
        return this.objecte;
    }
}
