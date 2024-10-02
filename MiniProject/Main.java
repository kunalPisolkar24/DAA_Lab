import java.util.Scanner;

public class Main {
    public static void naiveSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void rabinKarp(String text, String pattern) {
        int d = 256; 
        int q = 101;
        int n = text.length();
        int m = pattern.length();
        int i, j;
        int p = 0;
        int t = 0; 
        int h = 1;

        for (i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        for (i = 0; i <= n - m; i++) {
            if (p == t) {
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                if (t < 0) {
                    t = (t + q);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the text string: ");
        String text = sc.nextLine();

        System.out.println("Enter the pattern to search: ");
        String pattern = sc.nextLine();

        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Naive String Matching Algorithm");
            System.out.println("2. Rabin-Karp String Matching Algorithm");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\nNaive String Matching Algorithm:");
                    naiveSearch(text, pattern);
                    break;

                case 2:
                    System.out.println("\nRabin-Karp String Matching Algorithm:");
                    rabinKarp(text, pattern);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 3);

        sc.close();
    }
}
