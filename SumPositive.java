import java.util.Scanner;
import Constants; // Importing the Constants class

public class SumPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        System.out.println("Enter positive numbers to compute their sum. Enter a negative number to terminate input.");

        // Sentinel-controlled loop
        while (true) {
            System.out.print("?: ");
            int temp = scanner.nextInt();

            if (temp < 0) {
                break; // Terminate input on a negative number
            } else {
                sum += temp; // Add the positive number to the sum
            }
        }

        System.out.println("Sum of positive numbers: " + sum);
        
        scanner.close();
    }
}
