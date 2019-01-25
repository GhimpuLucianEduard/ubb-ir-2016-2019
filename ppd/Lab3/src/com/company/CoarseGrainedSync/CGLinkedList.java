package com.company.CoarseGrainedSync;

import com.company.Interfaces.Iterator;
import com.company.Interfaces.LinkedList;

import java.util.NoSuchElementException;

public class CGLinkedList implements LinkedList {

    private class Node
    {
        double value;
        Node next;

        public Node(double item)
        {
            this.value = item;
            next = null;
        }

        public double getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node head;

    public CGLinkedList()
    {
        head = null;
    }

    @Override
    public synchronized void insert(double item) {

        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
            return;
        } else if (item < head.getValue()) {
            newNode.next = head;
            head = newNode;
        } else {
            Node after = head.next;
            Node before = head;
            while (after != null) {
                if (item < after.getValue()) {
                    break;
                }
                before = after;
                after = after.next;
            }
            newNode.next = before.next;
            before.next = newNode;
        }
    }

    @Override
    public synchronized boolean delete(double value)
    {
        if(head == null)
            return false;

        if( head.value == value)
        {
            head = head.next;
            return true;
        }

        Node cur  = head;
        Node prev = null;

        while(cur != null && !(cur.value == value) )
        {
            prev = cur;
            cur = cur.next;
        }

        if(cur == null)
            return false;

        prev.next = cur.next;
        return true;
    }

    @Override
    public Iterator getIterator()
    {
        return new CGSortedLinkedListIterator(head);
    }

    class CGSortedLinkedListIterator implements Iterator {

        CGLinkedList.Node current;

        public CGSortedLinkedListIterator(CGLinkedList.Node current) {
            this.current = current;
        }

        @Override
        public synchronized double getNext() {
            if (!isValid()) {
                throw new NoSuchElementException("Iterator invalid!");
            }

            CGLinkedList.Node prev = current;
            double rez = current.getValue();
            current = current.getNext();
            return rez;
        }

        @Override
        public boolean isValid() {
            return current != null;
        }

        @Override
        public double getElement(int index) {
            return 0;
        }
    }
}
