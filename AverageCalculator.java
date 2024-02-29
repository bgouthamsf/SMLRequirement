import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) {
        try {
            File inputFile = new File("AverageFinder.txt"); // Change file name if needed
            Scanner fileScanner = new Scanner(inputFile);
            int totalNumbers = 7;
            int sum = 0;

            for (int i = 0; i < totalNumbers; i++) {
                int num = fileScanner.nextInt();
                sum += num;
            }

            double average = (double) sum / totalNumbers;
            System.out.println("Average of the seven numbers: " + average);
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
