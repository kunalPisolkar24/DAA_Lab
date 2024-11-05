import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left, right;

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        left = right = null;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String input = scanner.next();

        System.out.println("Enter the frequency of each character (space-separated):");
        int[] frequencies = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            frequencies[i] = scanner.nextInt();
        }

        System.out.println("Choose an option:");
        System.out.println("1. Huffman Encoding");
        System.out.println("2. Exit");
        int choice = scanner.nextInt();

        if (choice == 1) {
            huffmanEncoding(input, frequencies);
        }
    }

    public static void huffmanEncoding(String input, int[] frequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < input.length(); i++) {
            priorityQueue.add(new Node(input.charAt(i), frequencies[i]));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node newNode = new Node('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        Node root = priorityQueue.poll();
        Map<Character, String> codes = new HashMap<>();
        assignCodes(root, "", codes);

        System.out.println("Character\tFrequency\tCode \n===================================================");
        for (int i = 0; i < input.length(); i++) {
            System.out.println(input.charAt(i) + "      \t\t" + frequencies[i] + "\t\t" + codes.get(input.charAt(i)));
        }

        int originalSize = ;
        int compressedSize = 0;
        for (int i = 0; i < input.length(); i++) {
            originalSize += frequencies[i];
            compressedSize += codes.get(input.charAt(i)).length() * frequencies[i];
        }

        originalSize *= 8;

        System.out.println("\nOriginal size (bits): " + originalSize);
        System.out.println("Compressed size (bits): " + compressedSize);

        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            encodedMessage.append(codes.get(input.charAt(i)));
        }

        System.out.println("Encoded message: " + encodedMessage);
    }

    public static void assignCodes(Node node, String code, Map<Character, String> codes) {
        if (node == null) {
            return;
        }

        if (node.character != '\0') {
            codes.put(node.character, code);
        }

        assignCodes(node.left, code + "0", codes);
        assignCodes(node.right, code + "1", codes);
    }
}
