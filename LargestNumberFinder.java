import java.util.Scanner;

public class LargestNumberFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] memory = new int[100];
        int largestNumber = Integer.MIN_VALUE; // Initialize with the smallest possible integer value
        int instructionCounter = 0;

        System.out.println("*** Welcome to Simpletron! ***");
        System.out.println("*** Please enter your program one instruction ***");
        System.out.println("*** (or data word) at a time into the input ***");
        System.out.println("*** text field. I will display the location ***");
        System.out.println("*** number and a question mark (?). You then ***");
        System.out.println("*** type the word for that location. Enter ***");
        System.out.println("*** -99999 to stop entering your program. ***");

        // Read SML instructions into memory
        int index = 0;
        while (true) {
            System.out.printf("%02d ? ", index);
            int opcode = scanner.nextInt();

            if (opcode == -99999) {
                break;
            }

            memory[index++] = opcode;
        }

        System.out.println("*** Program loading completed ***");
        System.out.println("*** Program execution begins ***");

        // Execute SML instructions
        while (instructionCounter < index) {
            int opcode = memory[instructionCounter];

            switch (opcode) {
                case 10: // READ
                    System.out.print("Enter a number: ");
                    int number = scanner.nextInt();
                    if (number > largestNumber) {
                        largestNumber = number; // Update largestNumber if the entered number is larger
                    }
                    break;
                case 11: // WRITE
                    System.out.println("Largest number entered: " + largestNumber);
                    break;
                case 43: // HALT
                    System.out.println("Program terminated.");
                    return;
                case 50: // REMAINDER
                    int dividend = memory[++instructionCounter]; // Get the dividend
                    int divisor = memory[++instructionCounter]; // Get the divisor
                    int remainder = dividend % divisor; // Calculate the remainder
                    memory[++instructionCounter] = remainder; // Store the remainder in memory
                    break;
                case 51: // EXPONENTIATION
                    int base = memory[++instructionCounter]; // Get the base
                    int exponent = memory[++instructionCounter]; // Get the exponent
                    int result = (int) Math.pow(base, exponent); // Calculate the result
                    memory[++instructionCounter] = result; // Store the result in memory
                    break;
                case 52: // NEWLINE
                    System.out.println(); // Output a newline
                    break;
            }

            instructionCounter++;
        }

        scanner.close();
    }
}
