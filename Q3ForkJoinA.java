import java.util.ArrayList;

public class Q3ForkJoinA extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        ForkJoinQn instance = new ForkJoinQn(numbers);

        // PartA
        instance.parallelSumPartA();
    }
}
