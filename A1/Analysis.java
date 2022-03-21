package A1;
import java.util.ArrayList;

public class Analysis {
    public static void main(String args[]) {
        int NArray[] = {1000000, 10000000, 100000000};

        GenerateRandom obj = new GenerateRandom();
        ArrayList<Integer> numbers = obj.generateRandomArrayList(NArray[0]);
        // obj.printArrayList(numbers);

        System.out.println("Q1 Stream Sum");
        Q1Stream.TimeAnalysis(numbers);

        System.out.println("Q2 Parallel Stream Sum");
        Q2ParallelStream.TimeAnalysis(numbers);

        System.out.println("Q3 Fork Join A");
        Q3ForkJoinA.TimeAnalysis(numbers);

        System.out.println("Q3 Fork Join B");
        Q3ForkJoinB.TimeAnalysis(numbers);

        System.out.println("Q3 Async-Finish A");
        Q3AsyncFinishA.TimeAnalysis(numbers);

        // Heap space out of memory
        // System.out.println("Q3 Async-Finish B");
        // Q3AsyncFinishB.TimeAnalysis(numbers);
    }
}
