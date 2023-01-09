package coursera_algorithms.week_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

public class RandomizedQueueTest {
    @Test
    void addValuesToRq() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 4; i++) {
            rq.enqueue(i);
        }
        assertTrue(rq.size() == 4, "Number of elemnts must be 4");

        int num = 0;
        for (Integer v : rq) {
            num++;
        }
        assertTrue(num == 4, "Number of elemnts must be 4");
    }

    @Test
    void removeValuesFromRq() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }

        while (!rq.isEmpty()) {
            rq.dequeue();
        }

        int num = 0;
        for (Integer v : rq) {
            num++;
        }
        assertTrue(num == 0, "queue must be empty");
    }

    @Test
    void getRandomVals() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        ArrayList<Integer> nonRandVals = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
            nonRandVals.add(i);
        }

        ArrayList<Integer> randVals = new ArrayList<Integer>();
        for (Integer v : rq) {
            randVals.add(v);
        }
        assertNotEquals(randVals, nonRandVals,
                "Iterator must return random vals");
        Collections.sort(randVals);
        assertEquals(randVals, nonRandVals,
                "After sort the vals must be the same");
    }

}
