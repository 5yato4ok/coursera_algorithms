package coursera_algorithms.week_1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[] isOpen;
    private final int topIndex;
    private final int btmIndex;
    private int numOfOpenSites;
    private final int size;
    private final WeightedQuickUnionUF normalQU;
    private final WeightedQuickUnionUF backwashQU;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        topIndex = 0;
        btmIndex = n * n + 1;
        isOpen = new boolean[n * n + 2];
        size = n;
        backwashQU = new WeightedQuickUnionUF(n * n + 2);
        normalQU = new WeightedQuickUnionUF(n * n + 1);
        numOfOpenSites = 0;
        isOpen[topIndex] = true;
        isOpen[btmIndex] = true;
    }

    private int indexOf(int row, int col) {
        checkIsCorrect(row, col);
        return (row - 1) * size + col;
    }

    private void unionWithClose(int rowA, int colA, int rowB, int colB) {
        if (!isInCorrect(rowB, colB) && isOpen(rowB, colB)) {
            backwashQU.union(indexOf(rowA, colA), indexOf(rowB, colB));
            normalQU.union(indexOf(rowA, colA), indexOf(rowB, colB));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        numOfOpenSites += 1;
        int currIndex = indexOf(row, col);
        isOpen[currIndex] = true;
        if (row == 1) {
            backwashQU.union(currIndex, topIndex);
            normalQU.union(currIndex, topIndex);
        }
        if (row == size) {
            backwashQU.union(currIndex, btmIndex);
        }

        unionWithClose(row, col, row - 1, col);
        unionWithClose(row, col, row + 1, col);
        unionWithClose(row, col, row, col - 1);
        unionWithClose(row, col, row, col + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return isOpen[indexOf(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return normalQU.find(topIndex) == normalQU.find(indexOf(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return backwashQU.find(topIndex) == backwashQU.find(btmIndex);
    }

    private boolean isInCorrect(int row, int col) {
        return row < 1 || row > size || col < 1 || col > size;
    }

    private void checkIsCorrect(int row, int col) {
        if (isInCorrect(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Launch tests at PercolationTest.java");
    }
}
