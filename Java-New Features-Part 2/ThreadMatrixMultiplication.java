import java.util.ArrayList;
import java.util.Random;

public class ThreadMatrixMultiplication{
    ArrayList<ArrayList<Integer>> m1 = new ArrayList<>(), m2 = new ArrayList<>(), m3 = new ArrayList<>();

    static class MatrixMultiplier extends Thread {
        int startRow, endRow;
        ArrayList<ArrayList<Integer>> m1,m2,m3;
        MatrixMultiplier(int startRowArg, int endRowArg, ArrayList<ArrayList<Integer>> m1, ArrayList<ArrayList<Integer>> m2,ArrayList<ArrayList<Integer>> m3) {
            this.startRow = startRowArg;
            this.endRow = endRowArg;
            this.m1 = m1;
            this.m2 = m2;
            this.m3 = m3;
        }
        public void run() {
            for(int i=startRow;i<endRow;i++) {
                for(int j=0;j<m2.size();j++) {
                    for(int k=0;k<m2.get(0).size();k++) {
                        m3.get(i).set(j, m3.get(i).get(j) + (m1.get(i).get(k)*m2.get(k).get(j)));
                    }
                    System.out.println("ThreadNum:"+startRow/2+" and result at index:" + i+","+j+" is "+m3.get(i).get(j));
                }
            }
        }
    }

    ThreadMatrixMultiplication() {
        Random randomGenerator = new Random();
        for(int i=0;i<8;i++) {
            ArrayList<Integer> temprow1 = new ArrayList<>(), temprow2 = new ArrayList<>(), temprow3 = new ArrayList<>();
            for(int j=0;j<8;j++) {
                temprow1.add(randomGenerator.nextInt(10));
                temprow2.add(randomGenerator.nextInt(10));
                temprow3.add(0);
            }
            m1.add(temprow1);
            m2.add(temprow2);
            m3.add(temprow3);
        }
    }

    public void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for(int i=0;i<matrix.size();i++) {
            for(int j=0;j<matrix.get(i).size();j++) {
                System.out.print(matrix.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public void testMultiplicationOutput() {
        boolean wrongCalculation = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int Cij = 0;
                for (int k = 0; k < 8; k++) {
                    Cij += m1.get(i).get(k)*m2.get(k).get(j);
                }
                if(Cij!=m3.get(i).get(j)) {
                    wrongCalculation = true;
                    System.out.println("Calculation wrong for cell: " + i +","+j);
                }
            }
        }
        if(!wrongCalculation) {
            System.out.println("\nCalculation Verified, ouput is correct");
        }
    }

    public static void main(String args[]) {
        ThreadMatrixMultiplication instance = new ThreadMatrixMultiplication();
        MatrixMultiplier[] threads = new MatrixMultiplier[4];

        for(int i=0;i<4;i++) {
            threads[i] = new MatrixMultiplier(2*i,2*(i+1),instance.m1,instance.m2,instance.m3);
            threads[i].start();
        }
        for(int i=0;i<4;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Matrix A:");
        instance.printMatrix(instance.m1);

        System.out.println("\nMatrix B:");
        instance.printMatrix(instance.m2);

        System.out.println("\nMatrix C");
        instance.printMatrix(instance.m3);

        instance.testMultiplicationOutput();
    }
}
