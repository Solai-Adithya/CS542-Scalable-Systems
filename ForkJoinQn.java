import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumPartA extends RecursiveTask<Long> {
    int start;
    int end;
    ArrayList<Integer> arr;

    SumPartA(ArrayList<Integer> arrayArg, int startArg, int endArg) {
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

    public long computeSum(int start,int end) {
        long accumulator=0;
        for(int i=start;i<end;i++) {
            accumulator+=arr.get(i);
        }
        return accumulator;
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
            if(end-start>THRESHOLD) {
//                System.out.println("Making new subtasks: "+ start+", "+ end);
                int mid = (start+end)/2;
                SumPartB subtask1 = new SumPartB(start,mid);
                SumPartB subtask2 = new SumPartB(mid,end);
                subtask1.fork();
                return subtask1.join()+subtask2.compute();
            } else {
//                System.out.println("Direction computation: "+ start+", "+ end);
                return computeSum(start,end);
            }
        }
    }

    public void parallelSumPartA() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Long firstHalfSum = forkJoinPool.invoke(new SumPartA(arr,0,arr.size()/2));
        Long secondHalfSum = forkJoinPool.invoke(new SumPartA(arr,arr.size()/2, arr.size()));
        System.out.println(firstHalfSum+", "+secondHalfSum);
    }

    public long parallelSumPartB() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new SumPartB(0,arr.size()));
    }

    public static void main(String args[]) {
        ForkJoinQn instance = new ForkJoinQn();
        long arrsum = instance.parallelSumPartB();
        System.out.println("Array sum:" + arrsum);
    }

}