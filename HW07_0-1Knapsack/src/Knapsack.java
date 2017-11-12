import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int maxWeight = scan.nextInt();
        int[][] items = new int[n][2]; // [0]value, [1]weight
        int[][] opt = new int[n + 1][maxWeight + 1];
        for (int i = 0; i < n; i++) {
            items[i][0] = scan.nextInt(); // value
            items[i][1] = scan.nextInt(); // weight
        }

        boolean[][] updated = new boolean[n + 1][maxWeight + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int w = 0; w < maxWeight + 1; w++) {
                if (i == 0)
                    opt[i][w] = 0;
                else if (items[i - 1][1] > w)
                    opt[i][w] = opt[i - 1][w];
                else {
                    int left = opt[i - 1][w];
                    int right = items[i - 1][0] + opt[i - 1][w - items[i - 1][1]];

                    if (left >= right)
                        opt[i][w] = left;
                    else {
                        opt[i][w] = right;
                        updated[i][w] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < maxWeight + 1; j++)
                System.out.printf("%-3d ", opt[i][j]);
            System.out.println();
        }

        System.out.printf("\nmax : %d\n", opt[n][maxWeight]);

        System.out.printf("item : ");
        for (int i = n, j = maxWeight; i > 0; i--)
            if (updated[i][j]) {
                System.out.printf("%d ", i);
                j -= items[i - 1][1];
            }

        /*
5 11
1 1
6 2
18 5
22 6
28 7

         */
    }
}