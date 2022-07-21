package A1;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q3AsyncFinishB extends Template {
    public static Callable<Long> getSum(ArrayList<Integer> newArr, int low, int high) {
        Callable<Long> callable = () -> {
            if (high - low > 2) {
                ExecutorService executor = Executors.newFixedThreadPool(2);

                // Find sum of first half using a thread and then the second half using another
                Future<Long> sum1 = executor.submit(getSum(newArr, 0, newArr.size() / 2));
                Future<Long> sum2 = executor.submit(getSum(newArr, newArr.size() / 2, newArr.size()));

                try {
                    Long result1 = sum1.get();
                    Long result2 = sum2.get();

                    return result1 + result2;
                } catch (Exception e) {
                }

                executor.shutdown();
            } else {
                long accumulator = 0L;
                for (int i = low; i < high; i++) {
                    accumulator += newArr.get(i);
                }
                return accumulator;
            }

            return 0L;
        };
        return callable;
    }

    public static void methodToTime(ArrayList<Integer> numbers) {

        System.out.println("Check here");
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Long> sum1 = executor.submit(getSum(numbers, 0, numbers.size()));

        try {
            Long result1 = sum1.get();
            System.out.println("The sum is: " + result1);
        } catch (Exception e) {
        }

        executor.shutdown();
    }

    public static double TimeAnalysis(ArrayList<Integer> numbers) {
        long startTime = System.nanoTime();

        methodToTime(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.\n");
        return duration;
    }
}
