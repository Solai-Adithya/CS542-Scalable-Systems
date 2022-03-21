import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Sum extends RecursiveTask<Long> {
    int start;
    int end;
    ArrayList<Integer> arr;

    Sum(ArrayList<Integer> arrayArg, int startArg, int endArg) {
        this.arr = arrayArg;
        this.start = startArg;
        this.end = endArg;
    }

    protected Long compute(){
        long accumulator=0;
        for(int i=start;i<end;i++) {
            accumulator+=arr.get(i);
        }
        return accumulator;
    }
}

public class ForkJoinQn {

    public static void parallelSumPartA(ArrayList<Integer> arr) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Long firstHalfSum = forkJoinPool.invoke(new Sum(arr,0,arr.size()/2));
        Long secondHalfSum = forkJoinPool.invoke(new Sum(arr,arr.size()/2, arr.size()));
        System.out.println(firstHalfSum+", "+secondHalfSum);
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

        parallelSumPartA(numbers);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.");
    }

}