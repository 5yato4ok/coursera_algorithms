package coursera_algorithms.week_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PercolationTest {
    @Test
    void emptyGridNotPercolates() {
        Percolation system = new Percolation(4);
        assertFalse(system.percolates(), "empty grid should not percolate");
    }

    @Test
    void checkPercolation() {
        Percolation system = new Percolation(4);
        system.open(1, 1);
        system.open(2, 1);
        system.open(3, 1);
        system.open(4, 1);
        assertTrue(system.percolates(), "bottom and top should be connected");
    }

    @Test
    void checkIsOpen() {
        Percolation system = new Percolation(4);
        system.open(1, 2);
        assertTrue(system.isOpen(1, 2));
        assertFalse(system.isOpen(2, 3));
    }
}
