package com.kevin;

/**
 * Created by Kevin Kimaru Chege on 10/12/2017.
 */
public class SLinkedList {
    protected Node head;
    protected Node tail;
    protected long size;

    public SLinkedList() {
        head = null;
        tail = null;
        head.setNext(tail);
        size = 0;
    }

    public void addFirst(Node v) {
        v.setNext(head);
        head = v;
        size++;
    }

    public void addLast(Node v) {
        v.setNext(null);
        tail.setNext(v);
        tail = v;
        size++;
    }

    public void removeFirst() {
        if (head == null) throw new IllegalStateException("List is empty");
        Node t = head;
        head = head.getNext();
        t.setNext(null);
        size++;
    }
}
