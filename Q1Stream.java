import java.util.ArrayList;

public class Q1Stream extends Template {
    public static void methodToTime(ArrayList<Integer> numbers) {
        long sm = numbers.stream().map((vl) -> (long) vl).reduce(0L, Long::sum);
        System.out.println("The sum is: " + sm);
    }
}
