package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/12/2017.
 */

/**
 * Doubly linked list with nodes of type DNode storing strings
 */
public class DList {
    protected int size; //number of elements
    protected DNode header, trailer; //sentinels

    //constructor that creates an empty list
    public DList() {
        size = 0;
        header = new DNode(null, null, null);   //create header
        trailer = new DNode(null, header, null);   //create tailer
        header.setNext(trailer); //make header and  trailer point to each other
    }

    /**
     * returns the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the list is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the first node of the list
     */
    public DNode getFirst() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return header.getNext();
    }

    /**
     * Return the last node of the list
     */
    public DNode getLast() throws IllegalStateException {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return trailer.getPrev();
    }

    /**
     * returns the node before the given node v. An error occurs if v is the header
     */
    public DNode getPrev(DNode v) throws IllegalArgumentException {
        if (v == header) throw new IllegalArgumentException("Cannot move back past the header of the list");
        return v.getPrev();
    }

    /**
     * returns the node after the given node v. An error occurs if v is the trailer
     */
    public DNode getNext(DNode v) throws IllegalArgumentException {
        if (v == trailer) throw new IllegalArgumentException("Cannot move forward past the trailer of the list");
        return v.getNext();
    }

    /**
     * Inserts the given node z before the given node d. An error occurs if v is the header
     */
    public void addBefore(DNode v, DNode z) throws IllegalArgumentException {
        DNode u = getPrev(v); //may throw illegal argument exception
        z.setPrev(u);
        z.setNext(v);
        v.setPrev(z);
        u.setNext(z);
        size++;
    }

    /**
     * Inserts the given node z after the given node d. An error occurs if v is the trailer
     */
    public void addAfter(DNode v, DNode z) throws IllegalArgumentException {
        DNode w = getNext(v); //may throw illegal argument exception
        z.setPrev(v);
        z.setNext(w);
        w.setPrev(z);
        v.setNext(z);
        size++;
    }

    /**
     * Inserts the given node at the head of the list
     */
    public void addFirst(DNode v) {
        addAfter(header, v);
    }

    /**
     * Inserts the given node at the tail of the list
     */
    public void addLast(DNode v) {
        addBefore(header, v);
    }

    /**
     * Removes the given node v from the list.An error occurs if v is the header or trailer
     */
    public void remove(DNode v) {
        DNode u = getPrev(v);
        DNode w = getNext(v);
        //unlink the node from the list
        w.setPrev(u);
        w.setNext(w);
        v.setPrev(null);
        v.setNext(null);
    }

    /**
     * Returns whether a given node has a previous node
     */
    public boolean hasPrev(DNode v) {
        return v != header;
    }

    /**
     * Returns whether a given node has a next node
     */
    public boolean hasNext(DNode v) {
        return v != trailer;
    }

    /**
     * Insertion sort for a doubly linked list of class DList
     */
    public static void sort(DList l) {
        if (l.size() <= 1) return; //l is a already sorted in this case
        DNode pivot; //pivote node
        DNode ins; //insertion point
        DNode end = l.getFirst(); //end of run
        while (end != l.getLast()) {
            pivot = end.getNext(); //get the next pivot node
            l.remove(pivot); //remove it
            ins = end; //start searching from the end of the sorted run
            while (l.hasPrev(ins) && ins.getElement().compareTo(pivot.getElement()) > 0)
                ins = ins.getPrev(); //move left
            l.addAfter(ins, pivot); //add the pivot back after inserto=ion point
            if (ins == end)         //we just added pivot after end in this case
                end = end.getNext(); //so incremrnt the end marker
        }
    }

    /**
     * Return a string representation of a list
     */
    public String toString() {
        String s = "[";
        DNode v = header.getNext();
        while (v != trailer) {
            s += v.getElement();
            v = v.getNext();
            if (v != trailer) s += ",";
        }
        s += "]";
        return s;
    }

}
