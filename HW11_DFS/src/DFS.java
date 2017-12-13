import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class DFS {
    private static final int VERTICES = 8;
    private static final short MAX = Short.MAX_VALUE;
    private static int time;
    private static boolean[] visited = new boolean[VERTICES];
    private static int[] d = new int[VERTICES];
    private static int[] f = new int[VERTICES];
    private static int[] π = new int[VERTICES];

    public static void main(String[] args) {
        /* endVertex, cost */
        LinkedList<Integer>[] adjacencyList = new LinkedList[VERTICES];

        for (int i = 0; i < adjacencyList.length; ++i) {
            adjacencyList[i] = new LinkedList<>();
        }

        adjacencyList[0].add(1);
        adjacencyList[0].add(4);
        adjacencyList[1].add(0);
        adjacencyList[1].add(5);
        adjacencyList[2].add(3);
        adjacencyList[2].add(5);
        adjacencyList[2].add(6);
        adjacencyList[3].add(2);
        adjacencyList[3].add(6);
        adjacencyList[3].add(7);
        adjacencyList[4].add(0);
        adjacencyList[5].add(1);
        adjacencyList[5].add(2);
        adjacencyList[5].add(6);
        adjacencyList[6].add(2);
        adjacencyList[6].add(3);
        adjacencyList[6].add(5);
        adjacencyList[6].add(7);
        adjacencyList[7].add(3);
        adjacencyList[7].add(6);

        DFS(adjacencyList, 1);
    }

    public static void DFS(LinkedList[] G, int s) {
        for (int i = 0; i < G.length; i++)
            π[i] = -1;

        time = 0;

//        for (int u = 0; u < G.length; u++) {
//            if (!visited[u])
        DFS_VISIT(G, s);
//        }

        for (int i = 0; i < G.length; i++) {
            System.out.println("Node " + i + " => find : " + d[i] + ", complete : " + f[i] + ", π : " + π[i]);
        }
    }

    public static void DFS_VISIT(LinkedList[] G, int u) {
        time = time + 1;
        d[u] = time;
        visited[u] = true;
        for (Object v : G[u]) {
            if (!visited[(int) v]) {
                π[(int) v] = u;
                DFS_VISIT(G, (int) v);
            }
        }

        time = time + 1;
        f[u] = time;
    }
}
