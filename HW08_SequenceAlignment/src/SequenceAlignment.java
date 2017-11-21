import java.util.Scanner;

public class SequenceAlignment {
    private static final int GAP_PENALTY = 1; // δ
    private static final int MISMATCH_PENALTY = 2; // α
    private static final char UP = '↑';
    private static final char LEFT = '←';
    private static final char LEFTUPDIAGONAL = '↖';

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("string1 : ");
        String string1 = scan.nextLine();
        System.out.printf("string2 : ");
        String string2 = scan.nextLine();
        int[][] opt = new int[string1.length() + 1][string2.length() + 1];
        char[][] flowMap = new char[string1.length() + 1][string2.length() + 1];
        sequenceAlign(opt, flowMap, string1, string2);
        trackingRouteAndPrint(flowMap, opt, string1, string2);
    }

    private static void sequenceAlign(int[][] opt, char[][] flowMap, String string1, String string2) {
        for (int i = 0; i < string2.length() + 1; i++) {
            opt[0][i] = i * GAP_PENALTY;
            flowMap[0][i] = LEFT;
        }
        for (int i = 0; i < string1.length() + 1; i++) {
            opt[i][0] = i * GAP_PENALTY;
            flowMap[i][0] = UP;
        }

        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {
                opt[i][j] = GAP_PENALTY + opt[i - 1][j];
                flowMap[i][j] = UP;

                if (opt[i][j] > GAP_PENALTY + opt[i][j - 1]) {
                    opt[i][j] = GAP_PENALTY + opt[i][j - 1];
                    flowMap[i][j] = LEFT;
                }

                if (opt[i][j] > calMismatchPenalty(string1, string2, i - 1, j - 1) + opt[i - 1][j - 1]) {
                    opt[i][j] = calMismatchPenalty(string1, string2, i - 1, j - 1) + opt[i - 1][j - 1];
                    flowMap[i][j] = LEFTUPDIAGONAL;
                }
            }
        }
    }

    private static int calMismatchPenalty(String string1, String string2, int i, int j) {
        if (string1.charAt(i) == string2.charAt(j))
            return 0;
        return MISMATCH_PENALTY;
    }

    private static void trackingRouteAndPrint(char[][] flowMap, int[][] opt, String string1, String string2) {
        int start_i = flowMap.length - 1;
        int start_j = flowMap[0].length - 1;

        String[][] extendedArray = new String[opt.length * 2 + 1][opt[0].length * 2 + 1];

        // extend from opt to extendedArray
        for (int i = 0; i < extendedArray.length; i++) {
            for (int j = 0; j < extendedArray[0].length; j++) {
                if (i == 0 && j > 3 && j % 2 == 0)
                    extendedArray[i][j] = String.valueOf(string2.charAt((j - 4) / 2));
                else if (j == 0 && i > 3 && i % 2 == 0)
                    extendedArray[i][j] = String.valueOf(string1.charAt((i - 4) / 2));
                else if (i != 0 && i != 1 && j != 0 && j != 1 && i % 2 == 0 && j % 2 == 0)
                    extendedArray[i][j] = String.valueOf(opt[(i - 2) / 2][(j - 2) / 2]);
                else
                    extendedArray[i][j] = "";
            }
        }

        // tracking Route
        int m = extendedArray.length - 1;
        int n = extendedArray[0].length - 1;
        int i = start_i;
        int j = start_j;
        while ((i != 0 || j != 0) && m > 1 && n > 1) {
            if (flowMap[i][j] == UP) {
                extendedArray[m - 1][n] = String.valueOf(UP);
                m -= 2;
                i--;
            } else if (flowMap[i][j] == LEFTUPDIAGONAL) {
                extendedArray[m - 1][n - 1] = String.valueOf(LEFTUPDIAGONAL);
                m -= 2;
                n -= 2;
                i--;
                j--;
            } else if (flowMap[i][j] == LEFT) {
                extendedArray[m][n - 1] = String.valueOf(LEFT);
                n -= 2;
                j--;
            }
        }

        System.out.println("\nMIN COST : " + opt[opt.length - 1][opt[0].length - 1]);

        for (String[] row : extendedArray) {
            for (String item : row)
                System.out.printf("%2s", item);
            System.out.println("");
        }
    }
}
