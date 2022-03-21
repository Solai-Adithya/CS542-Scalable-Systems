import java.util.*;

public class Q2_Parallel_Stream {

    public static void methodToTime(ArrayList<Integer> numbers) {
        int sm = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println("The sum is: " + sm);
    }

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

        methodToTime(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.");
    }
}
