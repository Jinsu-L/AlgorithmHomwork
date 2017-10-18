import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
    precondition : A[1..n]은 정렬이 되어 있다.
    postcondition : find = x ∈ A[1..n], A[] 은 변하지 않는다.
    invariant : A[1..n] 배열 안에 key가 존재한다면 key는 A[l..r] 안에 있다.
 */
public class LoopInvariant {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] A = getData("./invariant_data.txt", 10000);
        System.out.print("찾을 수를 입력 : ");
        int find = scan.nextInt();
        BinarySearch(A, find);
    }

    private static void BinarySearch(int[] A, int find) {
        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (A[mid] == find) {
                System.out.println("[find] index : " + mid);
                return;
            } else if (A[mid] < find)
                l = mid + 1;
            else
                r = mid - 1;
        }
        System.out.println("값을 찾을 수 없습니다.");
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
}
