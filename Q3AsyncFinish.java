import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SumCalculator {
    ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    SumCalculator(ArrayList<Integer> newArr) {
        this.arr = newArr;
    }

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public Future<Long> calculateSum(int start, int end) {
        return executor.submit(() -> {
            long accumulator = 0;
            for (int i = start; i < end; i++) {
                accumulator += arr.get(i);
            }
            return accumulator;
        });
    }
}

public class Q3AsyncFinish extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        
        System.out.println("Check here");

        // Find sum of first half using a thread and then the second half using another thread
        SumCalculator instance = new SumCalculator(numbers);
        Future<Long> future1 = instance.calculateSum(0, numbers.size() / 2);
        Future<Long> future2 = instance.calculateSum(numbers.size() / 2, numbers.size());

        
        // if(!future1.isDone() || !future2.isDone()){
            try{
                Long result1 = future1.get();
                Long result2 = future2.get();

                System.out.println(result1+", "+result2);
            }catch(Exception e){
            }
        // }
    }

    public static double TimeAnalysis(ArrayList<Integer> numbers) {
        long startTime = System.nanoTime();

        methodToTime(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.\n");
        return duration;
    }

    public static void main(String args[]) {
        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(1000000);
        // obj.printArrayList(numbers);

        TimeAnalysis(numbers);
    }
}
