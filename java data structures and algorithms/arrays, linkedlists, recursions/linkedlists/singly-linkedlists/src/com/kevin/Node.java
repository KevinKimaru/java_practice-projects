package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/12/2017.
 */
public class Node {
    private String element; //we assume elements are strings
    private Node next;

    public Node(String s, Node n) {
        element = s;
        next = n;
    }

    /** Returns the element of this node */
    public String getElement() {
        return element;
    }

    /** Returns the next node of this node */
    public Node getNext() {
        return next;
    }

    /** Sets the element of this node */
    public void setElement(String newElem) {
        element = newElem;
    }

    /** Sets the next node of this node */
    public void setNext(Node newNext) {
        next = newNext;
    }
}
