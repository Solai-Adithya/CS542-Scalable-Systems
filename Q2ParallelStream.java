import java.util.ArrayList;

public class Q2ParallelStream extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        long sm = numbers.parallelStream().map((vl) -> (long) vl).reduce(0L, Long::sum);
        System.out.println("The sum is: " + sm);
    }
}
