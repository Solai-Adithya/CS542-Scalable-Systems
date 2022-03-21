import java.util.ArrayList;

public class Q1Stream extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        System.out.println("Stream method");
        long sm = numbers.stream().map((vl) -> (long) vl).reduce(0L, Long::sum);
        System.out.println("The sum is: " + sm);
    }

    public static double TimeAnalysis(ArrayList<Integer> numbers) {
        long startTime = System.nanoTime();

        methodToTime(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.\n");
        return duration;
    }


    public static void RunPart(int n) {
        // we will take n from the args
        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(n);
        // obj.printArrayList(numbers);

        TimeAnalysis(numbers);
    }

    public static void main(String args[]) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    int n = Integer.parseInt(args[i]);
                    RunPart(n);
                } catch (Exception err) {
                    System.out.println("error: " + err.getMessage());
                }
            }
        } else {
            // Default run for 1000 values
            RunPart(1000);
        }

        System.out.println();
    }

}
