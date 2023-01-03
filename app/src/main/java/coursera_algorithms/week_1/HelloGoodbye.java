package coursera_algorithms.week_1;
public class HelloGoodbye {

    public static void main(String[] args) {
        if (args.length == 2) {
            String first = args[0];
            String second = args[1];
            System.out.println("Hello " + first + " and " + second + ".");
            System.out.println("Goodbye " + second + " and " + first + ".");
        }
    }

}
