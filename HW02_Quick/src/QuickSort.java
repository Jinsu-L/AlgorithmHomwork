import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {

        final String filePath = "./input.txt";
        final int inputSize = 10000;
        int[] data = getData(filePath, inputSize);
        long start = System.currentTimeMillis();
        quickSort(data, 0, inputSize - 1);
        long time = System.currentTimeMillis() - start;
        System.out.println("time : " + time + "ms");
//        printList(data);
        saveList(data);
    }

    private static int[] getData(String filepath, int length) {
        try {
            FileReader file = new FileReader(filepath);
            Scanner scanner = new Scanner(file);
            int[] list = new int[length];
            int i = 0;
            while (scanner.hasNext())
                list[i++] = scanner.nextInt();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Error!!");
        return null;
    }

    private static void saveList(int[] list) {
        try {
            File file = new File("./201203391_quick.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(list[0] + "");
            for (int i = 1; i < list.length; i++)
                fileWriter.write(" " + list[i]);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void printList(int[] list) {
        System.out.println("SortedList : ");
        for (int aList : list)
            System.out.println(aList);
    }

    private static void quickSort(int[] list, int p, int r) {
        if (p <= r) {
            int pivot = partition(list, p, r);
            quickSort(list, p, pivot - 1);
            quickSort(list, pivot + 1, r);
        }
    }

    private static int partition(int[] list, int p, int r) {
        int pivot = list[r];
        int i = p;
        int j = r - 1;
        while (j != i - 1) {
            if (pivot >= list[i])
                i++;
            else if (pivot < list[j])
                j--;
            else {
                swap(list, i, j);
                i++;
                j--;
            }
        }
        swap(list, i, r);
        return i;
    }

    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}

