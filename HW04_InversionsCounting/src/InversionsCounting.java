import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InversionsCounting {
    public static void main(String[] args) {
        String file_1 = "Dataset/data05_inversion_01.txt";
        String file_2 = "Dataset/data05_inversion_02.txt";
        String file_3 = "Dataset/data05_inversion_03.txt";
        String file_4 = "Dataset/data05_inversion_04.txt";
        long[] arr_1 = getArray(file_1);
        long[] arr_2 = getArray(file_2);
        long[] arr_3 = getArray(file_3);
        long[] arr_4 = getArray(file_4);

        System.out.println("Input Data : " + Arrays.toString(arr_1));
        System.out.println("Output Data : " + sortAndCount(arr_1));

        System.out.println("Input Data : " + Arrays.toString(arr_2));
        System.out.println("Output Data : " + sortAndCount(arr_2));

        System.out.println("Input Data : " + Arrays.toString(arr_3));
        System.out.println("Output Data : " + sortAndCount(arr_3));

        System.out.println("Input Data : " + Arrays.toString(arr_4));
        System.out.println("Output Data : " + sortAndCount(arr_4));

    }

    public static long sortAndCount(long[] L) {
        if (L.length <= 1)
            return 0;


        long[] A = new long[L.length / 2];
        long[] B = new long[L.length - A.length];
        System.arraycopy(L, 0, A, 0, A.length);
        System.arraycopy(L, A.length, B, 0, B.length);

        long num_A = sortAndCount(A);
        long num_B = sortAndCount(B);

        long num_merge = mergeAndCount(A, B, L);
        return num_A + num_B + num_merge;
    }

    public static long mergeAndCount(long[] A, long[] B, long[] L) {
        int indexA = 0, indexB = 0, indexInversion = 0;
        int inversion_count = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA] > B[indexB]) {
                inversion_count = inversion_count + A.length - indexA;
                L[indexInversion] = B[indexB];
                indexB++;
            } else {
                L[indexInversion] = A[indexA];
                indexA++;
            }

            indexInversion++;
        }
        while (indexA < A.length) {
            L[indexInversion] = A[indexA];
            indexA++;
            indexInversion++;
        }
        while (indexB < B.length) {
            L[indexInversion] = B[indexB];
            indexB++;
            indexInversion++;
        }
        return inversion_count;
    }

    public static long[] getArray(String filepath) {
        ArrayList<Long> list = new ArrayList();

        try {
            FileReader file = new FileReader(filepath);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLong()) {
                list.add(scan.nextLong());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list.stream().mapToLong(Long::longValue).toArray();
    }
}
