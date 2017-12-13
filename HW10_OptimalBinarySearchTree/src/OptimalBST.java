import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalBST {
    private static ArrayList<Float> p = new ArrayList<>();
    private static ArrayList<Float> q = new ArrayList<>();
    private static float[][] e;
    private static float[][] w;
    private static int[][] root;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Dataset/data11.txt"));

        while (scan.hasNext()) {
            p.add(scan.nextFloat());
            q.add(scan.nextFloat());
        }

        e = new float[p.size() + 1][p.size() + 1];
        w = new float[p.size() + 1][p.size() + 1];
        root = new int[p.size()][p.size()];
        OptimalBST(p, q, p.size());
    }

    private static void OptimalBST(List p, List q, int n) {
        for (int i = 1; i < n + 1; i++) {
            e[i][i - 1] = (Float) q.get(i - 1);
            w[i][i - 1] = (Float) q.get(i - 1);
        }
        int j;
        float t;
        for (int l = 1; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                j = i + l - 1;
                e[i][j] = Float.MAX_VALUE / 2;
                w[i][j] = w[i][j - 1] + (Float) p.get(j) + (Float) q.get(j);
                for (int r = i; r < j + 1; r++) {
                    t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        System.out.printf("%.2f",e[1][n - 1]);
    }
}
