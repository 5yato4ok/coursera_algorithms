package coursera_algorithms.week_2;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class NodeList {
        NodeList(Item val) {
            item = val;
        }

        Item item;
        NodeList next;
        NodeList prev;
    }

    private int size;
    private NodeList start;
    private NodeList end;

    // construct an empty deque
    public Deque() {
        start = null;
        end = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addFirst called with null");
        }
        size++;
        NodeList newNode = new NodeList(item);
        if (start == null) {
            start = newNode;
            end = start;
            return;
        }

        NodeList savedNext = start;
        start = newNode;
        start.next = savedNext;
        start.next.prev = start;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addLast called with null");
        }

        size++;
        NodeList newNode = new NodeList(item);
        if (start == null) {
            start = newNode;
            end = start;
            return;
        }
        NodeList savedPrev = end;
        end = newNode;
        end.prev = savedPrev;
        end.prev.next = end;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("remove called on empty Deque");
        }
        NodeList saved = start;
        if (size() == 1) {
            size--;
            start = null;
            end = null;
            return saved.item;
        }
        size--;
        start = saved.next;
        start.prev = null;
        return saved.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("remove called on empty Deque");
        }

        NodeList saved = end;
        if (size() == 1) {
            size--;
            start = null;
            end = null;
            return saved.item;
        }
        size--;
        end = saved.prev;
        end.next = null;
        return saved.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private NodeList current = start;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.print("Check DequeTest.java");
    }

}
