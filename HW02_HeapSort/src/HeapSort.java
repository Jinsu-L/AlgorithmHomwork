import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HeapSort {
    private static int size = 0;

    public static void main(String[] args) {
        final int MAXSIZE = 1000;
        FileReader file = null;
        try {
            file = new FileReader("./data_heap.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File NotFound!!");
        }
        Scanner scanner = new Scanner(file);
        Node[] nodes = new Node[MAXSIZE];

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(", ");
            nodes[++size] = new Node(Integer.parseInt(line[0]), line[1]);
        }
        build_Max_Heap(nodes);
        scanner = new Scanner(System.in);
        System.out.println("\n**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");
        boolean run = true;
        while (run) {
            printHeap(nodes);
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    System.out.print("신규 작업명 (20 Bytes 이내) : ");
                    String jab = scanner.nextLine();
                    System.out.print("우선 순위 (0 ~ 999) : ");
                    int priority = scanner.nextInt();
                    insert(nodes, new Node(priority, jab));
                    System.out.println("**** 작업 추가 완료 ****\n");
                    break;
                case 2:
                    Node maxNode = max(nodes);
                    System.out.println(maxNode == null ? "\n출력 가능한 작업이 존재하지 않습니다.\n" : "\n**** MAX : " + maxNode.values + ", " + maxNode.subject + " ****\n");
                    break;
                case 3:
                    Node extract_Node = extract_max(nodes);
                    System.out.println(extract_Node == null ? "\n삭제 가능한 작업이 존재하지 않습니다.\n" : "\n**** 한 개의 작업을 처리했습니다. ****\n");
                    break;
                case 4:
                    System.out.print("수정할 노드 x 입력 : ");
                    int modifyIndex = Integer.parseInt(scanner.nextLine());
                    System.out.print("수정할 key : ");
                    int modifyKey = Integer.parseInt(scanner.nextLine());
                    Node increaseNode = increase_key(nodes, modifyIndex, modifyKey);
                    System.out.println(increaseNode == null ? "\n기존 키보다 작은 키로 변경할 수 없습니다. \n" : "\n**** 한 개의 작업을 처리했습니다. ****");
                    break;
                case 5:
                    System.out.print("삭제할 노드 x 입력 : ");
                    int removeIndex = Integer.parseInt(scanner.nextLine());
                    Node deleteNode = delete(nodes, removeIndex);
                    System.out.println(deleteNode == null ? "\n삭제 가능한 작업이 존재하지 않습니다.\n" : "\n**** 한 개의 작업을 처리했습니다. ****\n");
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("\n**** (1~6) 사이에 값을 입력해 주세요! ****");
                    break;
            }
        }
    }

    private static void printHeap(Node[] nodes) {
        for (int i = 1; i <= size; i++)
            System.out.println(nodes[i]);

        System.out.println("\n---------------------------------------------");
        System.out.println("1. 작업 추가    2. 최댓값  3. 최대 우선순위 작업 처리");
        System.out.println("4. 원소 키값 증가 5. 작업제거 6.종료");
        System.out.println("-----------------------------------------------");
    }

    private static void insert(Node[] nodes, Node x) {
        nodes[++size] = x;
        build_Max_Heap(nodes);

    }

    private static Node max(Node[] nodes) {
        return size >= 1 ? nodes[1] : null;
    }

    private static Node extract_max(Node[] nodes) {
        Node tmpNode = size >= 1 ? nodes[1] : null;
        if (tmpNode != null) {
            swap(nodes, 1, size);
            nodes[size--] = null;
            heapify(nodes, 1);
        }
        return tmpNode;
    }

    private static Node increase_key(Node[] nodes, int x, int k) {
        Node tmpNode = nodes[x].values < k ? nodes[x] : null;
        if (tmpNode != null) {
            tmpNode.values = k;
            build_Max_Heap(nodes);
        }
        return tmpNode;
    }

    private static Node delete(Node[] nodes, int x) {
        Node tmpNode = x <= size ? nodes[x] : null;
        if (tmpNode != null) {
            swap(nodes, x, size);
            nodes[size--] = null;
            heapify(nodes, x);
        }
        return tmpNode;
    }

    private static void build_Max_Heap(Node[] nodes) {
        for (int i = size / 2; i > 0; i--)
            heapify(nodes, i);
    }

    private static void heapify(Node[] nodes, int index) {
        int left = 2 * index;
        int right = 2 * index + 1;
        int largest = index;
        if (left <= size && nodes[left].values > nodes[largest].values) {
            largest = left;
        } else {
            largest = index;
        }
        if (right <= size && nodes[right].values > nodes[largest].values) {
            largest = right;
        }

        if (largest != index) {
            swap(nodes, largest, index);
            heapify(nodes, largest);
        }

    }

    private static void swap(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }
}

class Node {
    String subject = "";
    int values;

    Node(int values, String subject) {
        this.values = values;
        this.subject = subject;
    }

    public String toString() {
        return values + ", " + subject;
    }
}
