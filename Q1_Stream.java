import java.util.*;
import java.util.stream.*;

public class Q1_Stream {

    public static void main(String args[]) {

        int n = 1000;
        // we will take n from the args
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(n);
        // obj.printArrayList(numbers);

        long startTime = System.nanoTime();


        int sm = numbers.stream().reduce(0, Integer::sum);
        System.out.println("The sum is: " + sm);


        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.");
    }
}
