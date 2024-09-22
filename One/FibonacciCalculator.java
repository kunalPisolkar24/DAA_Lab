import java.util.Scanner;

public class FibonacciCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a positive integer to calculate its Fibonacci number:");
        int n = scanner.nextInt();

        System.out.println("Choose a method to calculate the Fibonacci number:");
        System.out.println("1. Recursive Method");
        System.out.println("2. Non-Recursive Method");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("The Fibonacci number is: " + fibonacciRecursive(n));
                break;
            case 2:
                System.out.println("The Fibonacci number is: " + fibonacciNonRecursive(n));
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 0) {
            return -1; 
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    public static int fibonacciNonRecursive(int n) {
        if (n <= 0) {
            return -1; 
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            int a = 0, b = 1;
            for (int i = 2; i < n; i++) {
                int temp = a + b;
                a = b;
                b = temp;
            }
            return b;
        }
    }
}