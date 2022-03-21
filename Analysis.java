public class Analysis {
    public static void main(String args[]) {
        String myArgs[] = {"1000000", "10000000", "100000000"};

        System.out.println("Q1 Stream Sum");
        Q1Stream.TimeAnalysis(myArgs);

        System.out.println("Q2 Parallel Stream Sum");
        Q2ParallelStream.TimeAnalysis(myArgs);

        System.out.println("Q3 Fork Join A");

        System.out.println("Q3 Fork Join B");
    }
}
