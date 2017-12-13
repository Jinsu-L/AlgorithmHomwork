import sun.misc.Queue;

import java.util.LinkedList;

public class BFS {
    private static final int VERTICES = 8;
    private static final short MAX = Short.MAX_VALUE;

    public static void main(String[] args) {
        /* endVertex, cost */
        LinkedList<Integer>[] adjacencyList = new LinkedList[VERTICES];
        boolean[] visited = new boolean[VERTICES];
        int[] distance = new int[VERTICES];
        int[] π = new int[VERTICES];

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

        BFS(adjacencyList, distance, visited, π, 1);
    }

    public static void BFS(LinkedList[] G, int[] d, boolean[] visited, int[] π, int s) {
        for (int i = 0; i < G.length; i++) {
            d[i] = MAX;
            π[i] = -1;
        }
        visited[s] = true;
        d[s] = 0;
        π[s] = -1;

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);

        while (!q.isEmpty()) {
            try {
                int u = q.dequeue();
                for (Object v : G[u]) {
                    if (!visited[(int) v]) {
                        visited[(int) v] = true;
                        d[(int) v] = d[u] + 1;
                        π[(int) v] = u;
                        q.enqueue((Integer) v);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        for (int i = 0; i < G.length; i++) {
            System.out.println("Node " + i + " => cost : " + d[i] + ", π : " + π[i]);
        }
    }
}
