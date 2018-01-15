package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/21/2017.
 */
public class CircleList {
    protected Node cursor; //current cursor
    protected int size;

    public CircleList() {
        cursor = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * Returns the cursor
     */
    public Node getCursor() {
        return cursor;
    }

    /** Moves the cursor forward */
    public void advance() {
        cursor = cursor.getNext();
    }

    /** adds a node after the cursor */
    public void add(Node newNode) {
        if (cursor == null) { //list is empty
            newNode.setNext(newNode);
            cursor = newNode;
        } else {
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
        }
        size++;
    }

    /** Removes the node after the cursor */
    public Node remove() {
        Node oldNode = cursor.getNext();
        if (oldNode == cursor) cursor = null; //list is becoming empty
        else {
            cursor.setNext(oldNode.getNext()); //link out the old node
            oldNode.setNext(null);
        }
        return oldNode;
    }

    /** Returns a string representation starting from the cursor */
    public String toString() {
        if (cursor == null) return "[]";
        String s = "[..." + cursor.getElement();
        Node oldCursor = cursor;
        for (advance(); oldCursor != cursor; advance()) {
            s += ", " + cursor.getElement();
        }
        return s + "...]";
    }

}

