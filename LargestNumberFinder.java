import java.util.Scanner;

public class LargestNumberFinder {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Read the number of numbers to be processed
            System.out.print("Enter the number of numbers to be processed: ");
            int count = scanner.nextInt();

            if (count <= 0) {
                System.out.println("No numbers to process. Exiting...");
                return;
            }

            // Read the first number to initialize the largestNumber
            System.out.print("Enter number 1: ");
            int largestNumber = scanner.nextInt();

            // Read and compare the remaining numbers to find the largest one
            for (int i = 2; i <= count; i++) {
                System.out.print("Enter number " + i + ": ");
                int number = scanner.nextInt();
                if (number > largestNumber) {
                    largestNumber = number;
                }
            }

            // Display the largest number
            System.out.println("The largest number is: " + largestNumber);
        }
    }
}
