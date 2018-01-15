package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/12/2017.
 */
/** Node of a doubly linked list of strings */
public class DNode {
    protected String element;   //string element stored by a node
    protected DNode next, prev;     //pointers to previous and next nodes

    public DNode(String e, DNode p, DNode n) {
        element = e;
        prev = p;
        next = n;
    }

    /** Returns the element of this node */
    public String getElement() {
        return element;
    }
    /** Returns the previous node of this node */
    public DNode getPrev() {
        return prev;
    }

    /** Returns the next node of this node */
    public DNode getNext() {
        return next;
    }
    /** Sets the element of this node */
    public void setElement(String newElem) {
        element = newElem;
    }

    /** Sets the previous node of this node */
    public void setPrev(DNode newPrev) {
        prev = newPrev;
    }
    /** Sets the next node of this node */
    public void setNext(DNode newNext) {
        next = newNext;
    }
}
