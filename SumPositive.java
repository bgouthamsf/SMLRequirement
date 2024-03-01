import java.util.Scanner;

public class SumPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] memory = new int[100];
        int accumulator = 0;
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

            if (opcode != 10 && opcode != 11 && opcode != 43 && opcode != 50 && opcode != 51 && opcode != 52) {
                System.out.println("Invalid opcode.");
                return;
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
                    System.out.print("Enter a value: ");
                    int temp = scanner.nextInt();
                    if (temp < 0) {
                        break; // Terminate input on a negative number
                    } else {
                        accumulator += temp; // Add the positive number to the sum
                    }
                    break;
                case 11: // WRITE
                    System.out.println("Sum of positive numbers: " + accumulator);
                    break;
                case 43: // HALT
                    System.out.println("Program terminated.");
                    return;
                case 50: // REMAINDER
                    System.out.print("Enter the first number: ");
                    int num1 = scanner.nextInt();
                    System.out.print("Enter the second number: ");
                    int num2 = scanner.nextInt();
                    int remainder = num1 % num2;
                    System.out.println("Remainder: " + remainder);
                    break;
                case 51: // EXPONENTIATION
                    System.out.print("Enter the base: ");
                    int base = scanner.nextInt();
                    System.out.print("Enter the exponent: ");
                    int exponent = scanner.nextInt();
                    double result = Math.pow(base, exponent);
                    System.out.println("Result: " + result);
                    break;
                case 52: // NEWLINE
                    System.out.println();
                    break;
            }

            instructionCounter++;
        }

        scanner.close();
    }
}
