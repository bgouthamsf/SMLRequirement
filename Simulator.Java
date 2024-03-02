import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
    static final int READ = 10;
    static final int WRITE = 11;
    static final int LOAD = 20;
    static final int STORE = 21;
    static final int ADD = 30;
    static final int SUBTRACT = 31;
    static final int MULTIPLY = 32;
    static final int DIVIDE = 33;
    static final int REMAINDER = 34;
    static final int EXPONENT = 35;
    static final int BRANCH = 40;
    static final int BRANCH_NEG = 41;
    static final int BRANCH_ZERO = 42;
    static final int HALT = 43;

    private int accumulator;
    private int instructionCounter;
    private int operand;
    private int operationCode;
    private int instructionRegister;
    private int[] memory;

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.runSimulator();
    }
    
    public Simulator() {
        accumulator = 0;
        instructionCounter = 0;
        operand = 0;
        operationCode = 0;
        instructionRegister = 0;
        memory = new int[100];
    }

    public void runSimulator() {
        initializeRegisters();
        printInstructions();
        loadInstructions();
        execute();
        dump();
    }

    private void initializeRegisters() {
        // Implementation for initialization if needed
    }

    private void printInstructions() {
        String display = "*** Welcome to Simpletron! ***\n" +
                         "*** Please enter your program one instruction ***\n" +
                         "*** (or data word) at a time into the input ***\n" +
                         "*** text field. I will display the location ***\n" +
                         "*** number and a question mark (?). You then ***\n" +
                         "*** type the word for that location. Enter ***\n" +
                         "*** -99999 to stop entering your program ***";
        System.out.println(display);
    }

    private void loadInstructions() {
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/gouthambommu/Documents/SMLRequirement/input_instructions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int instruction = Integer.parseInt(line);
                if (instruction == -99999 || counter >= 100) {
                    break;
                }
                if (validate(instruction)) {
                    memory[counter] = instruction;
                    counter++;
                } else {
                    System.out.println("Input invalid");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*** Program loading completed ***");
    }

    private static boolean validate(int value) {
        return -9999 <= value && value <= 9999;
    }

    private boolean testOverflow() {
        if (!validate(accumulator)) {
            System.out.println("*** Fatal error. Accumulator overflow. ***");
            return true;
        }
        return false;
    }

    private void execute() {
        System.out.println("*** Program execution begins ***");
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/gouthambommu/Documents/SMLRequirement/inputFile.txt"))) {
            String line;
            int lineNo = 0;
            while (instructionCounter < memory.length) {
                instructionRegister = memory[instructionCounter];
                operationCode = instructionRegister / 100;
                operand = instructionRegister % 100;

                instructionCounter++;
                if (operationCode == READ) {
                    memory[operand] = Integer.parseInt(br.readLine());
                    lineNo++;
                } else if (operationCode == WRITE) {
                    System.out.printf("Contents of %02d is %d%n", operand, memory[operand]);
                } else if (operationCode == LOAD) {
                    accumulator = memory[operand];
                } else if (operationCode == STORE) {
                    memory[operand] = accumulator;
                } else if (operationCode == ADD) {
                    accumulator += memory[operand];
                    if (testOverflow()) return;
                } else if (operationCode == SUBTRACT) {
                    accumulator -= memory[operand];
                    if (testOverflow()) return;
                } else if (operationCode == MULTIPLY) {
                    accumulator *= memory[operand];
                    if (testOverflow()) return;
                } else if (operationCode == DIVIDE) {
                    if (memory[operand] == 0) {
                        System.out.println("*** Fatal error. Attempt to divide by zero. ***");
                        return;
                    }
                    accumulator /= memory[operand];
                } else if (operationCode == REMAINDER) {
                    if (memory[operand] == 0) {
                        System.out.println("*** Fatal error. Attempt to divide by zero. ***");
                        return;
                    }
                    accumulator %= memory[operand];
                } else if (operationCode == EXPONENT) {
                    accumulator = (int) Math.pow(accumulator, memory[operand]);
                    if (testOverflow()) return;
                } else if (operationCode == BRANCH) {
                    instructionCounter = operand;
                } else if (operationCode == BRANCH_NEG) {
                    if (accumulator < 0) {
                        instructionCounter = operand;
                    }
                } else if (operationCode == BRANCH_ZERO) {
                    if (accumulator == 0) {
                        instructionCounter = operand;
                    }
                } else if (operationCode == HALT) {
                    System.out.println("*** Simpletron execution terminated ***");
                    break;
                } else {
                    System.out.println("*** Fatal error. Invalid operation code. ***");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayRegisters() {
        System.out.println("REGISTERS:");
        System.out.printf("%-24s%+05d%n", "Accumulator:", accumulator);
        System.out.printf("%-27s%02d%n", "InstructionCounter:", instructionCounter);
        System.out.printf("%-24s%+05d%n", "InstructionRegister:", instructionRegister);
        System.out.printf("%-27s%02d%n", "OperationCode:", operationCode);
        System.out.printf("%-27s%02d%n", "Operand:", operand);
    }

    private void dump() {
        displayRegisters();
        System.out.println("\nMEMORY:");
        System.out.print(" ");
        for (int k = 0; k < 10; k++) {
            System.out.printf("%7d", k);
        }
        System.out.println();

        for (int k = 0; k < 10; k++) {
            System.out.printf("%02d", k * 10);
            for (int i = 0; i < 10; i++) {
                System.out.printf(" %+05d", memory[k * 10 + i]);
            }
            System.out.println();
        }
    }
}

class SimulatorTest {
    public static void main(String[] args) {
        Simulator simpletron = new Simulator();
        simpletron.runSimulator();
    }
}
