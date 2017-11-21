import java.util.Scanner;
/*
input example 1 :
string1 : ocurrance
string2 : occurrence
output :
MIN COST : 3

input example 2:
string1 : CTGACCTACCT
string2 : CCTGACTACAT
output :
MIN COST : 4

 */

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
        int[][] opt = new int[string1.length()][string2.length()];

        sequenceAlign(opt, string1, string2);
        printOpt(opt);
    }

    private static void sequenceAlign(int[][] opt, String string1, String string2) {
        for (int i = 0; i < string2.length(); i++)
            opt[0][i] = i * GAP_PENALTY;

        for (int i = 0; i < string1.length(); i++)
            opt[i][0] = i * i * GAP_PENALTY;


        for (int i = 1; i < string1.length(); i++) {
            for (int j = 1; j < string2.length(); j++) {
                opt[i][j] = Math.min(calMismatchPenalty(string1, string2, i, j) + opt[i - 1][j - 1], Math.min(GAP_PENALTY + opt[i - 1][j], GAP_PENALTY + opt[i][j - 1]));
            }
        }
    }

    private static int calMismatchPenalty(String string1, String string2, int i, int j) {
        if (string1.charAt(i) == string2.charAt(j))
            return 0;
        return MISMATCH_PENALTY;
    }

    private static void printOpt(int[][] opt) {
        int m = opt.length - 1;
        int n = opt[0].length - 1;
        System.out.println("MIN COST : " + opt[m][n] + "\n");


    }
}
