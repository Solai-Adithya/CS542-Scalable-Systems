package A1;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinQn {
    ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    public ForkJoinQn(ArrayList<Integer> arrArg) {
        this.arr = arrArg;
    }

    public long computeSum(int start, int end) {
        long accumulator = 0;
        for (int i = start; i < end; i++) {
            accumulator += arr.get(i);
        }
        return accumulator;
    }

    class SumPartA extends RecursiveTask<Long> {
        int start;
        int end;

        SumPartA(int startArg, int endArg) {
            this.start = startArg;
            this.end = endArg;
        }

        protected Long compute() {
            return computeSum(start, end);
        }
    }

    class SumPartB extends RecursiveTask<Long> {
        int start;
        int end;
        int THRESHOLD = 2;

        SumPartB(int startArg, int endArg) {
            this.start = startArg;
            this.end = endArg;
        }

        protected Long compute() {
            if (end - start > THRESHOLD) {
                int mid = (start + end) / 2;
                SumPartB subtask1 = new SumPartB(start, mid);
                SumPartB subtask2 = new SumPartB(mid, end);
                subtask1.fork();
                return subtask1.join() + subtask2.compute();
            } else {
                return computeSum(start, end);
            }
        }
    }

    public void parallelSumPartA() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Long firstHalfSum = forkJoinPool.invoke(new SumPartA(0, arr.size() / 2));
        Long secondHalfSum = forkJoinPool.invoke(new SumPartA(arr.size() / 2, arr.size()));
        System.out.println(firstHalfSum + ", " + secondHalfSum);
    }

    public long parallelSumPartB() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SumPartB(0, arr.size()));
    }

    public static void main(String args[]) {
        // USE PART WISE A and B instead of this main

        int n = 1000;
        // we will take n from the args
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(n);
        // obj.printArrayList(numbers);

        long startTime = System.nanoTime();
        ForkJoinQn instance = new ForkJoinQn(numbers);

        // PartA
        instance.parallelSumPartA();

        // PartB
        // long arrsum = instance.parallelSumPartB();
        // System.out.println("Array sum:" + arrsum);

        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1000000.0;
        System.out.println("Time takes to calculate sum = " + duration + " milliseconds.");
    }

}