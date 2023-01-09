package coursera_algorithms.week_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
    Item[] storage;
    int lastPos;
    int capacity;

    // construct an empty randomized queue
    public RandomizedQueue() {
        storage = (Item[]) new Object[1];
        capacity = 1;
        lastPos = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return lastPos == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return lastPos;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (lastPos < 0 || capacity == lastPos) {
            enlarge();
        }
        storage[lastPos] = item;
        lastPos++;
    }

    private void enlarge() {
        if (capacity == 0) {
            capacity = 1;
            lastPos = 0;
            storage = (Item[]) new Object[1];
        } else {
            resize(capacity * 2);
        }
    }

    private void shrink() {
        if (capacity == 0) {
            return;
        }
        int newCapacity = capacity / 2;
        if (newCapacity == 0) {
            storage = null;
            lastPos = -1;
            capacity = 0;
        } else {
            resize(newCapacity);
        }
    }

    private void resize(int newCapacity) {
        Item[] tmp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < lastPos; i++) {
            tmp[i] = storage[i];
        }
        storage = tmp;
        capacity = newCapacity;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randIndx = StdRandom.uniformInt(capacity);
        Item val = storage[randIndx];
        lastPos--;
        storage[randIndx] = storage[lastPos];
        storage[lastPos] = null;

        if (lastPos < capacity / 2) {
            shrink();
        }

        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return storage[StdRandom.uniformInt(lastPos)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIt(storage, lastPos);
    }

    private class RandomizedQueueIt implements Iterator<Item> {
        Item[] storage;
        int curLastPos;
        int[] indexes;

        RandomizedQueueIt(Item[] vals, int valSize) {
            storage = vals;
            curLastPos = valSize;
            indexes = new int[curLastPos];
            for (int i = 0; i < curLastPos; i++) {
                indexes[i] = i;
            }
        }

        public boolean hasNext() {
            return curLastPos > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int randVal = StdRandom.uniformInt(curLastPos);
            int valRanIndx = indexes[randVal];
            Item val = storage[valRanIndx];
            curLastPos--;
            indexes[randVal] = indexes[curLastPos];
            indexes[curLastPos] = 0;
            return val;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.print("Check unit-test java");
    }

}
