import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LargestNumberFinder {
    public static void main(String[] args) {
        try {
            File inputFile = new File("LargestNumberFile.txt"); // Change file name if needed
            Scanner fileScanner = new Scanner(inputFile);
            int count = fileScanner.nextInt();

            if (count <= 0) {
                System.out.println("No numbers to process. Exiting...");
                fileScanner.close();
                return;
            }

            int largestNumber = fileScanner.nextInt();
            for (int i = 2; i <= count; i++) {
                int number = fileScanner.nextInt();
                if (number > largestNumber) {
                    largestNumber = number;
                }
            }

            System.out.println("The largest number is: " + largestNumber);
            fileScanner.close();

            // Memory dump (optional)
            dumpMemory();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static void dumpMemory() {
        // Add code to dump memory here
        System.out.println("Memory dump:");
        // Implement memory dump logic here
    }
}
