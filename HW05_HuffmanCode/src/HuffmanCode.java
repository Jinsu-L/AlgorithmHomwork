import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("Dataset/HuffmanCode/data06_huffman.txt");
        Scanner scan = new Scanner(fileReader);
        String s = scan.nextLine();
        huffman(s);
    }

    private static void huffman(String s) {
        ArrayList<MinHeap.Node> list = frequencyChars(s.toCharArray());
        MinHeap heap = new MinHeap();

        for (MinHeap.Node node : list)
            heap.insert(node);

        while (heap.size > 1) {
            MinHeap.Node left = heap.extract_min();
            MinHeap.Node right = heap.extract_min();
            MinHeap.Node newNode = new MinHeap.Node(left.values + right.values, left, right);
            heap.insert(newNode);
        }

        printHuffmanTree(heap.extract_min(), "");
    }

    private static ArrayList<MinHeap.Node> frequencyChars(char[] chars) {
        HashMap<Character, MinHeap.Node> hashMap = new HashMap<>();
        for (char c : chars) {
            if (hashMap.containsKey(c))
                hashMap.put(c, new MinHeap.Node(c, hashMap.get(c).values + 1));
            else
                hashMap.put(c, new MinHeap.Node(c, 1));
        }

        return new ArrayList<>(hashMap.values());
    }

    private static void printHuffmanTree(MinHeap.Node node, String str) {
        if (node.c != '\0') {
            System.out.println(node.c + ", " + str);
        } else {
            printHuffmanTree(node.left, str + "0");
            printHuffmanTree(node.right, str + "1");
        }
    }

    public static class MinHeap {
        final int MAXSIZE = 1000;
        int size = 0;
        Node[] nodes;

        MinHeap() {
            nodes = new Node[MAXSIZE];
            build_Min_Heap();
        }

        void insert(Node x) {
            nodes[++size] = x;
            build_Min_Heap();
        }

        Node extract_min() {
            Node tmpNode = size >= 1 ? nodes[1] : null;
            if (tmpNode != null) {
                swap(nodes, 1, size);
                nodes[size--] = null;
                heapify(1);
            }
            return tmpNode;
        }


        private void build_Min_Heap() {
            for (int i = size / 2; i > 0; i--)
                heapify(i);
        }

        private void heapify(int index) {
            int left = 2 * index;
            int right = 2 * index + 1;
            int largest = index;
            if (left <= size && nodes[left].values < nodes[largest].values)
                largest = left;
            else
                largest = index;
            if (right <= size && nodes[right].values < nodes[largest].values)
                largest = right;
            if (largest != index) {
                swap(nodes, largest, index);
                heapify(largest);
            }
        }

        private void swap(Node[] nodes, int i, int j) {
            Node tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }

        static class Node {
            char c = '\0';
            int values;
            Node left;
            Node right;

            Node(char c, int values) {
                this.values = values;
                this.c = c;
                left = null;
                right = null;
            }

            Node(int values, Node left, Node right) {
                this.values = values;
                this.left = left;
                this.right = right;
            }
        }

    }

}
