import java.util.Scanner;

public class SMLInterpreterWithExtensions {
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
            System.out.printf("%2d ? ", index);
            int instruction = scanner.nextInt();

            if (instruction == -99999) {
                break;
            }

            memory[index++] = instruction;
        }

        System.out.println("*** Program loading completed ***");
        System.out.println("*** Program execution begins ***");

        // Execute SML instructions
        while (instructionCounter < index) {
            int opcode = memory[instructionCounter] / 100;
            int operand = memory[instructionCounter] % 100;

            switch (opcode) {
                case 10: // READ
                    System.out.print("Enter a value: ");
                    memory[operand] = scanner.nextInt();
                    break;
                case 11: // WRITE
                    System.out.println(memory[operand]);
                    break;
                case 20: // LOAD
                    accumulator = memory[operand];
                    break;
                case 21: // STORE
                    memory[operand] = accumulator;
                    break;
                case 30: // ADD
                    accumulator += memory[operand];
                    break;
                case 31: // SUBTRACT
                    accumulator -= memory[operand];
                    break;
                case 32: // DIVIDE
                    if (memory[operand] == 0) {
                        System.out.println("*** Attempt to divide by zero ***");
                        System.out.println("*** Simpletron execution abnormally terminated ***");
                        return;
                    }
                    accumulator /= memory[operand];
                    break;
                case 33: // MULTIPLY
                    accumulator *= memory[operand];
                    break;
                case 40: // BRANCH
                    instructionCounter = operand - 1; // -1 to compensate for the automatic increment
                    break;
                case 41: // BRANCHNEG
                    if (accumulator < 0) {
                        instructionCounter = operand - 1;
                    }
                    break;
                case 42: // BRANCHZERO
                    if (accumulator == 0) {
                        instructionCounter = operand - 1;
                    }
                    break;
                case 43: // HALT
                    System.out.println("Program terminated.");
                    return;
                case 50: // REM (Remainder calculation)
                    accumulator %= memory[operand];
                    break;
                case 51: // EXP (Exponentiation calculation)
                    accumulator = (int) Math.pow(accumulator, memory[operand]);
                    break;
                case 52: // NEWLINE (Output a newline)
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid opcode.");
                    return;
            }

            instructionCounter++;
        }

        scanner.close();
    }
}
