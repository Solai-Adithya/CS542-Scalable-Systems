package A1;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Q3AsyncFinishA extends Template{
    public static Callable<Long> getSum(ArrayList<Integer> newArr, int low, int high){
        Callable<Long> callable = () -> {
            Long accumulator = 0L;
            for(int i=low;i<high;i++){
                accumulator+=newArr.get(i);
            }
            return accumulator;
        };
        return callable;
    }
    
    public static void methodToTime(ArrayList<Integer> numbers) {

        System.out.println("Check here");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Long> sum1 = executor.submit(getSum(numbers, 0, numbers.size()/2));
        Future<Long> sum2 = executor.submit(getSum(numbers, numbers.size()/2,numbers.size()));

        try{
            Long result1 = sum1.get();
            Long result2 = sum2.get();

            System.out.println(result1+", "+result2);
            System.out.println(result1+result2);
        }catch(Exception e){}

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
