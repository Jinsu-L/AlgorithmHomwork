import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BellmanFord {
    public static int[][] adjancyMatrix;
    public static boolean[][] exist;
    public static long[] distance;
    public static int numOfNode;
    public static int s;
    public static int t;
    public static int numOfEdge;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Dataset/data10.txt"));
        numOfNode = scan.nextInt();
        s = scan.nextInt();
        t = scan.nextInt();
        numOfEdge = scan.nextInt();
        adjancyMatrix = new int[numOfNode][numOfNode];
        exist = new boolean[numOfNode][numOfNode];
        distance = new long[numOfNode];

        for (int i = 0; i < numOfEdge; i++) {
            int m = scan.nextInt();
            int n = scan.nextInt();
            adjancyMatrix[m][n] = scan.nextInt();
            exist[m][n] = true;
        }

        bellmanFord();
    }

    public static void bellmanFord() {
        for (int i = 0; i < numOfNode; i++) {
            if (i == s)
                distance[i] = 0;
            else
                distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < numOfNode - 1; i++) {
            for (int u = 0; u < numOfNode; u++) {
                for (int v = 0; v < numOfNode; v++) {
                    if (exist[u][v] && distance[v] > distance[u] + adjancyMatrix[u][v])
                        distance[v] = distance[u] + adjancyMatrix[u][v];
                }
            }
        }
        boolean cycle = false;
        for (int u = 0; u < numOfNode && !cycle; u++) {
            for (int v = 0; v < numOfNode && !cycle; v++) {
                if (exist[u][v] && distance[v] > distance[u] + adjancyMatrix[u][v]) {
                    System.out.println("negative-weight cycle exists");
                    cycle = true;
                }
            }
        }
        if (!cycle)
            System.out.println(distance[t]);
    }
}
