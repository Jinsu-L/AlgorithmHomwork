import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MinimizingLatenssProblem {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("Dataset/LatenessProblem/data06_lateness.txt");
        Scanner scan = new Scanner(fileReader);
        int n = scan.nextInt();

        ArrayList<Pair> list = new ArrayList<>();

        for (int i = 0; i < n; i++)
            list.add(new Pair(scan.nextInt(), scan.nextInt()));

        list.sort(Comparator.comparing(Pair::getD));

        int t = 0;
        int lateness = 0;

        for (Pair p : list) {
            int f = t + p.t;
            t = t + p.t;
            lateness = Math.max(lateness, f - p.d);
        }

        System.out.println(lateness);

    }

    public static class Pair {
        int t;
        int d;

        Pair(int t, int d) {
            this.t = t;
            this.d = d;
        }

        public int getD() {
            return d;
        }
    }
}
