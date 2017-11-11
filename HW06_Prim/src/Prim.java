import java.util.PriorityQueue;

public class Prim {

    public static void main(String[] args) {
        int maxValue = Integer.MAX_VALUE;
        int[][] matrix = {
                {0, 4, maxValue, maxValue, maxValue, maxValue, maxValue, 8, maxValue},
                {4, 0, 8, maxValue, maxValue, maxValue, maxValue, 11, maxValue},
                {maxValue, 8, 0, 7, maxValue, 4, maxValue, maxValue, 2},
                {maxValue, maxValue, 7, 0, 9, 14, maxValue, maxValue, maxValue},
                {maxValue, maxValue, maxValue, 9, 0, 10, maxValue, maxValue, maxValue},
                {maxValue, maxValue, 4, 14, 10, 0, 2, maxValue, maxValue},
                {maxValue, maxValue, maxValue, maxValue, maxValue, 2, 0, 1, 6},
                {8, 11, maxValue, maxValue, maxValue, maxValue, 1, 0, 7},
                {maxValue, maxValue, 2, maxValue, maxValue, maxValue, 6, 7, 0}
        };
        int MAXSIZE = 9;
        int cost = 0;
        boolean[] visit = new boolean[MAXSIZE];

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(-65, 0, 0)); // 출력시 공백을 위해 ascii => -65 + 'a' = ' '

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!visit[edge.child]) {
                visit[edge.child] = true;
                cost += edge.cost;
                System.out.printf("w<%c, %c> = %d\n", (char) ('a' + edge.parent), (char) ('a' + edge.child), edge.cost);
                for (int i = 0; i < MAXSIZE; i++)
                    if (matrix[edge.child][i] != 0 && matrix[edge.child][i] != maxValue)
                        queue.add(new Edge(edge.child, i, matrix[edge.child][i]));
            }
        }

        System.out.println("\nw<MST> = " + cost);
    }

    public static class Edge implements Comparable<Edge> {
        int parent, child;
        int cost;

        Edge(int parent, int child, int cost) {
            this.parent = parent;
            this.child = child;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }

}