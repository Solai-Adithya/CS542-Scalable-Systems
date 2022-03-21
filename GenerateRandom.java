import java.util.ArrayList;

public class GenerateRandom {

    public void printArrayList(ArrayList<Integer> arl) {
        for (int i = 0; i < arl.size(); i++) {
            System.out.print(arl.get(i) + " ");
        }
        System.out.println();
    }

    public int generateRandom() {
        return (int) (Math.random() * 100);
    }

    public ArrayList<Integer> generateRandomArrayList(int n) {
        System.out.println("Generating random numbers for n = " + n);

        ArrayList<Integer> arl = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arl.add(generateRandom());
        }

        return arl;
    }

    public static void main(String args[]) {
        GenerateRandom obj = new GenerateRandom();
        obj.printArrayList(obj.generateRandomArrayList(1000));
    }
}
