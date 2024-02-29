import java.util.Scanner;

public class AverageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNumbers = 7;
        int sum = 0;

        System.out.println("Enter seven numbers:");

        // Counter-controlled loop to read seven numbers
        for (int i = 0; i < totalNumbers; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            int num = scanner.nextInt();
            sum += num;
        }

        double average = (double) sum / totalNumbers;

        System.out.println("Average of the seven numbers: " + average);
        
        scanner.close();
    }
}
