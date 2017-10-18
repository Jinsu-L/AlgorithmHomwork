import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InversionsCounting {
    static int counter = 0;
    public static void main(String[] args) {
        String file_1 = "Dataset/data05_inversion_01.txt";
        String file_2 = "Dataset/data05_inversion_02.txt";
        String file_3 = "Dataset/data05_inversion_03.txt";
        String file_4 = "Dataset/data05_inversion_04.txt";
        int[] arr_1 = getArray(file_1);
        int[] arr_2 = getArray(file_2);
        int[] arr_3 = getArray(file_3);
        int[] arr_4 = getArray(file_4);
        sortAndCount(arr_1);
        System.out.println(counter);

    }

    public static int[] sortAndCount(int[] L) {
        if (L.length <= 1)
            return L;


        int[] firstHalf = new int[L.length / 2];
        int[] secondHalf = new int[L.length - firstHalf.length];
        System.arraycopy(L, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(L, firstHalf.length, secondHalf, 0, secondHalf.length);

        sortAndCount(firstHalf);
        sortAndCount(secondHalf);   //go recursion!

        mergeAndCount(firstHalf, secondHalf, L);    //and merge it
        return L;
    }

    public static void mergeAndCount(int[] firstHalf, int[] secondHalf, int[] result) {
        int currentPosOfFirstHalf = 0, currentPosOfSecondHalf = 0, currentPosOfResult = 0;
        while (currentPosOfFirstHalf < firstHalf.length && currentPosOfSecondHalf < secondHalf.length) {
            if (firstHalf[currentPosOfFirstHalf] < secondHalf[currentPosOfSecondHalf]) {
                result[currentPosOfResult] = firstHalf[currentPosOfFirstHalf];
                ++currentPosOfFirstHalf;
            } else if (firstHalf[currentPosOfFirstHalf] > secondHalf[currentPosOfSecondHalf]) {
                result[currentPosOfResult] = secondHalf[currentPosOfSecondHalf];
                ++currentPosOfSecondHalf;
                counter = counter + firstHalf.length - currentPosOfFirstHalf;
            }

            ++currentPosOfResult;
        }
        while (currentPosOfFirstHalf != firstHalf.length) {    //yes, i wanna copy it myself
            result[currentPosOfResult] = firstHalf[currentPosOfFirstHalf];
            ++currentPosOfFirstHalf;
            ++currentPosOfResult;
        }
        while (currentPosOfSecondHalf != secondHalf.length) {    //and one more time
            result[currentPosOfResult] = secondHalf[currentPosOfSecondHalf];
            ++currentPosOfSecondHalf;
            ++currentPosOfResult;
        }
    }

    public static int[] getArray(String filepath) {
        ArrayList<Integer> list = new ArrayList();

        try {
            FileReader file = new FileReader(filepath);

            Scanner scan = new Scanner(file);

            while (scan.hasNextInt()) {
                list.add(scan.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
