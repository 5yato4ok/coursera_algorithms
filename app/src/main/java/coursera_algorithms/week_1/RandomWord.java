package coursera_algorithms.week_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String wordToPrint = "";
        String line = null;
        while (!StdIn.isEmpty()) {
            line = StdIn.readString();
            if (StdRandom.bernoulli()) {
                wordToPrint = line;
            }
        }
        if (wordToPrint.equals("")) {
            wordToPrint = line;
        }
        StdOut.print(wordToPrint);
    }
}
