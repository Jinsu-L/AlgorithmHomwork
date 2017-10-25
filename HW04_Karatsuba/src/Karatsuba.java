import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Karatsuba {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("Dataset/data05_karatsuba.txt");
        Scanner scan = new Scanner(fileReader);

        long A = scan.nextLong();
        long B = scan.nextLong();
        System.out.println("Input Data : ");
        System.out.println(A);
        System.out.println(B);
        System.out.println("Output Data : " + karatsuba(A, B));
    }

    public static long karatsuba(long A, long B) {
        if (A < 10 || B < 10) {
            return A * B;
        }

        String toStringA = Long.toString(A);
        int m = toStringA.length() / 2;

        long x1 = (long) (A / Math.pow(10, Math.round(m)));
        long x2 = (long) (A % Math.pow(10, Math.round(m)));
        long y1 = (long) (B / Math.pow(10, Math.round(m)));
        long y2 = (long) (B % Math.pow(10, Math.round(m)));
        long z0 = karatsuba(x2, y2);
        long z2 = karatsuba(x1, y1);
        long z1 = karatsuba((x2 + x1), (y2 + y1)) - z2 - z0;

        return (long) ((z2 * Math.pow(10, Math.round(m * 2))) + (z1 * Math.pow(10, Math.round(m))) + z0);
    }
}
