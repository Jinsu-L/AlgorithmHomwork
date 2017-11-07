import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    static int MAXINTEGER = Integer.MAX_VALUE;
    static int MAXSIZE = 5;
    private static int[][] matrix = {
            {0, 10, 3, MAXINTEGER, MAXINTEGER},
            {MAXINTEGER, 0, 1, 2, MAXINTEGER},
            {MAXINTEGER, 4, 0, 8, 2},
            {MAXINTEGER, MAXINTEGER, MAXINTEGER, 0, 7},
            {MAXINTEGER, MAXINTEGER, MAXINTEGER, 9, 0}
    };
    static boolean[] visit = new boolean[MAXSIZE];

    public static void main(String[] args) {
        System.out.println("Dijkstra's Algorithm.\n");

        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());

        int start = 0;

        int[] dist = new int[MAXSIZE];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        queue.add(new Pair<>(start, 0));
        int count_s = 0;
        while (!queue.isEmpty()) {
            Pair u = queue.poll();
            if (!visit[(int) u.getKey()]) {
                visit[(int) u.getKey()] = true;
                System.out.println("===========================================");
                System.out.println("S[" + count_s++ + "] : d[" + (char) ((int) u.getKey() + 'A') + "] = " + dist[(int) u.getKey()]);
                System.out.println("-------------------------------------------");
                int count = 0;
                for (int i = 0; i < MAXSIZE; i++) {
                    if (!visit[i])
                        if (matrix[(Integer) u.getKey()][i] != Integer.MAX_VALUE && dist[i] > (Integer) u.getValue() + matrix[(Integer) u.getKey()][i]) {
                            System.out.printf("Q[" + count++ + "] : d[" + (char) (i + 'A') + "] = " + dist[i]);
                            dist[i] = (Integer) u.getValue() + matrix[(Integer) u.getKey()][i];
                            queue.add(new Pair<>(i, dist[i]));
                            System.out.println("-> d[" + (char) (i + 'A') + "] = " + dist[i]);
                        } else
                            System.out.println("Q[" + count++ + "] : d[" + (char) (i + 'A') + "] = " + dist[i]);
                }
            }
        }
    }
}