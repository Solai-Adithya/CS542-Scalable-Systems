import java.util.*;

public class Q2ParallelStream {

    public static void methodToTime(ArrayList<Integer> numbers) {
        long sm = numbers.parallelStream().map((vl) -> (long) vl).reduce(0L, Long::sum);
        System.out.println("The sum is: " + sm);
    }

    public static double RunPart(int n) {
        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(n);
        // obj.printArrayList(numbers);

        long startTime = System.nanoTime();

        methodToTime(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.");
        return duration;
    }

    public static ArrayList<Double> TimeAnalysis(String args[]) {
        // we will take n from the args

        ArrayList<Double> times = new ArrayList<Double>();

        if (args.length > 0) {
            for(int i = 0; i < args.length; i++) {
                try {
                    int n = Integer.parseInt(args[i]);
                    times.add(RunPart(n));
                } catch(Exception err) {
                    System.out.println("error: " + err.getMessage());
                }
            }
        } else {
            // Default run for 1000 values
            times.add(RunPart(1000));
        }

        System.out.println();
        return times;
    }

    public static void main(String args[]) {
        TimeAnalysis(args);
    }
}
