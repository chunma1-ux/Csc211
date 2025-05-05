import java.util.Scanner;

public class FibonacciDecimalBinary {

    // Recursive Fibonacci
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative Fibonacci
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // Recursive Decimal to Binary
    public static String decimalToBinaryRecursive(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }
        return decimalToBinaryRecursive(n / 2) + (n % 2);
    }

    // Iterative Decimal to Binary
    public static String decimalToBinaryIterative(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder binary = new StringBuilder();
        while (n > 0) {
            binary.insert(0, n % 2);
            n /= 2;
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter n for Fibonacci: ");
            int n = sc.nextInt();
             if (n < 0) {
                System.out.println("Fibonacci input must be a non-negative integer.");
            } else {
                System.out.println("Fibonacci Recursive (" + n + "): " + fibonacciRecursive(n));
                System.out.println("Fibonacci Iterative (" + n + "): " + fibonacciIterative(n));
            }


            System.out.print("Enter decimal: ");
            int decimal = sc.nextInt();
            if (decimal < 0) {
                System.out.println("Decimal input must be a non-negative integer.");
            } else {
                System.out.println("Decimal to Binary Recursive (" + decimal + "): " + decimalToBinaryRecursive(decimal));
                System.out.println("Decimal to Binary Iterative (" + decimal + "): " + decimalToBinaryIterative(decimal));
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } finally {
            sc.close();
        }
    }
}

