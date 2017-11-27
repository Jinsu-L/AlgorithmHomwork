import java.util.HashSet;
import java.util.Scanner;

public class SequenceAlignment {
    private static final int GAP_PENALTY = 1; // δ
    private static final int MISMATCH_PENALTY = 2; // α
    private static int[][] opt;
    private static HashSet<Pair> ArrowPath = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("string1 : ");
        String string2 = scan.nextLine();
        System.out.printf("string2 : ");
        String string1 = scan.nextLine();
        Align(string1, string2, 0, 0);
        for (Pair p : ArrowPath) {
            System.out.printf("(%d, %d)", p.x, p.y);
        }
    }

    private static void Align(String string1, String string2, int x_s, int y_s) {
        int n = string1.length();
        int m = string2.length();
        if (n <= 2 || m <= 2) {
            ArrowPath.add(new Pair(x_s, y_s));
            standardAlignment(string1, string2);
            tracking(x_s, y_s, n, m);
            return;
        }

        int[] YPrefix = AllYPrefixCosts(string1, n / 2, string2);
        int[] YSuffix = AllSuffixCosts(string1, n / 2, string2);


        int best = Integer.MAX_VALUE;
        int bestq = 0;
        int cost;
        for (int q = 1; q < m; q++) {
            cost = YPrefix[q] + YSuffix[q];
            if (cost <= best) {
                best = cost;
                bestq = q;
            }
        }

        ArrowPath.add(new Pair(n / 2 + x_s, bestq + y_s));
        Align(string1.substring(0, n / 2), string2.substring(0, bestq), x_s, y_s);
        Align(string1.substring(n / 2, n), string2.substring(bestq, m), x_s + n / 2, y_s + bestq);
    }

    private static int[] AllSuffixCosts(String string1, int mid, String string2) {

        StringBuilder newSentence = new StringBuilder();
        for (int i = string1.length() - 1; i >= mid - 1; i--) {
            newSentence.append(string1.charAt(i));
        }
        StringBuilder newSentence2 = new StringBuilder();
        for (int i = string2.length() - 1; i >= 0; i--) {
            newSentence2.append(string2.charAt(i));
        }

        standardAlignment(newSentence.toString(), newSentence2.toString());

        int[] result = opt[mid + 1];
        for (int i = 0; i < result.length / 2; i++) {
            int temp = result[i];
            result[i] = result[result.length - i - 1];
            result[result.length - i - 1] = temp;
        }
        return result;
    }

    private static int[] AllYPrefixCosts(String sentence1, int mid, String sentence2) {
        standardAlignment(sentence1.substring(0, mid), sentence2);
        return opt[mid];
    }

    private static void standardAlignment(String sentence1, String sentence2) {
        int n = sentence1.length();
        int m = sentence2.length();
        opt = new int[n + 1][m + 1];
        for (int i = 0; i < m + 1; i++) {
            opt[0][i] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            opt[i][0] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                opt[i][j] = Integer.min(calculateMisMisMatch(sentence1, sentence2, i, j) + opt[i - 1][j - 1], Integer.min(opt[i - 1][j] + GAP_PENALTY, opt[i][j - 1] + GAP_PENALTY));
            }
        }
    }

    private static void tracking(int x_s, int y_s, int n, int m) {
        int i = n, j = m;
        while (i != 0 || j != 0) {

            ArrowPath.add(new Pair(x_s + i, y_s + j));
            if (i == 0)
                j--;
            else if (j == 0)
                i--;
            else if (opt[i - 1][j] < opt[i][j])
                i--;
            else if (opt[i][j - 1] < opt[i][j])
                j--;
            else if (opt[i - 1][j - 1] <= opt[i][j]) {
                i--;
                j--;
            }
        }
    }

    private static int calculateMisMisMatch(String string1, String string2, int i, int j) {
        return string1.charAt(i - 1) == string2.charAt(j - 1) ? 0 : MISMATCH_PENALTY;
    }


    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}