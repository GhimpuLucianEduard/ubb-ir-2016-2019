package com.company.GrainSync;

import com.company.Interfaces.Iterator;
import com.company.Interfaces.LinkedList;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GSLinkedList implements LinkedList {

    public class Node {

        private Node next;
        private double value;
        public Lock lock = new ReentrantLock();

        public Node() {
        }

        public Node(Node next, double value) {
            this.next = next;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }


    private Node first;

    public GSLinkedList() {
        Node max = new Node(null, Integer.MAX_VALUE);
        this.first = new Node(max, Integer.MAX_VALUE);
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    @Override
    public void insert(double value) {

        Node noteToAdd = new Node(null, value);

//        if (first == null) {
//            first = noteToAdd;
//            return;
//        }

        first.lock.lock();
        Node last = first;
        try {
            Node current = first.getNext();
            current.lock.lock();
            try {
                while (current.getValue() < value) {
                    last.lock.unlock();
                    last = current;
                    current = last.getNext();
                    current.lock.lock();
                }
                if (current.getValue() == value) {
                    return;
                }
                Node newNode = new Node(current, value);
                last.setNext(newNode);
                return;
            } finally {
                if (current != null) {
                    current.lock.unlock();
                }
            }
        } finally {
            last.lock.unlock();
        }
    }

    @Override
    public boolean delete(double value) {

//        if (first == null) {
//            return false;
//        }

        Node currentNode = null, lastNode = null;

        first.lock.lock();

        try {
            lastNode = first;
            currentNode = first.getNext();
            currentNode.lock.lock();
            try {
                while (currentNode.getValue() < value) {
                    lastNode.lock.unlock();
                    lastNode = currentNode;
                    currentNode = lastNode.getNext();
                    currentNode.lock.lock();
                }
                if (currentNode.getValue() == value) {
                    lastNode.setNext(currentNode.getNext());
                    return true;
                }
                return false;
            } finally {
                if (currentNode != null) {
                    currentNode.lock.unlock();
                }
            }
        } finally {
            lastNode.lock.unlock();
        }
    }

    @Override
    public Iterator getIterator() {
        return new SortedLinkedListIterator(first);
    }

    public void printList() {
        Node currentNode = first;
        int index = 0;
        while (currentNode != null) {
            System.out.printf("List[%d] = %f \n", index, currentNode.getValue());
            index++;
            currentNode = currentNode.getNext();
        }
    }

    class SortedLinkedListIterator implements Iterator {

        Node current = new Node();

        public SortedLinkedListIterator(Node current) {
            this.current = current;
        }

        @Override
        public double getNext() {
            if (!isValid()) {
                throw new NoSuchElementException("Iterator invalid!");
            }

            current.lock.lock();
            Node prev = current;
            double rez = current.getValue();
            current = current.getNext();
            prev.lock.unlock();
            return rez;
        }

        @Override
        public boolean isValid() {
            return current != null;
        }

        @Override
        public double getElement(int index) {
            int currentIndex = 0;

            if (!isValid()) {
                throw new NoSuchElementException("Iterator invalid!");
            }

            double rez = current.getValue();

            while (isValid()) {
                if (currentIndex == index) {
                    return rez;
                } else {
                    rez = getNext();
                    currentIndex++;
                }
            }

            if (!isValid()) {
                throw new IndexOutOfBoundsException("Index out of bound!");
            }

            return rez;
        }
    }
}
