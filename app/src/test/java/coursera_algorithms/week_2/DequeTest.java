package coursera_algorithms.week_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

public class DequeTest {
    @Test
    void addValuesToDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        Deque<Integer> dequeEnd = new Deque<Integer>();
        for (int i = 0; i < 4; i++) {
            deque.addFirst(i);
            dequeEnd.addLast(i);
        }
        assertTrue(deque.size() == 4, "Number of elemnts must be 4");

        int num = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList<Integer> resEnd = new ArrayList<Integer>();
        for (Integer v : deque) {
            num++;
            res.add(v);
        }
        assertTrue(num == 4, "Number of elemnts must be 4");
        num = 0;
        for (Integer v : dequeEnd) {
            num++;
            resEnd.add(v);
        }

        assertTrue(num == 4, "Number of elemnts must be 4");
        Collections.reverse(res);
        assertEquals(res, resEnd);

    }

    @Test
    void removeValuesToDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        Deque<Integer> dequeEnd = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            dequeEnd.addLast(i);
        }

        while (!deque.isEmpty()) {
            deque.removeFirst();
            deque.removeLast();
        }

        int num = 0;
        for (Integer v : deque) {
            num++;
        }
        assertTrue(num == 0, "deque must be empty");
        while (!dequeEnd.isEmpty()) {
            dequeEnd.removeFirst();
            dequeEnd.removeLast();
        }

        num = 0;
        for (Integer v : dequeEnd) {
            num++;
        }
        assertTrue(num == 0, "deque must be empty");
    }

}
