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
    static ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    public static void parallelSumPartA() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Long firstHalfSum = forkJoinPool.invoke(new Sum(arr,0,arr.size()/2));
        Long secondHalfSum = forkJoinPool.invoke(new Sum(arr,arr.size()/2, arr.size()));
        System.out.println(firstHalfSum+", "+secondHalfSum);
    }

    public static void main(String args[]) {
        parallelSumPartA();
    }

}