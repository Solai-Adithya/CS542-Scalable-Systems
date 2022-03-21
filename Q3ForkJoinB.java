import java.util.ArrayList;

public class Q3ForkJoinB extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        ForkJoinQn instance = new ForkJoinQn(numbers);

        // PartB
        long arrsum = instance.parallelSumPartB();
        System.out.println("Array sum:" + arrsum);
    }
}
