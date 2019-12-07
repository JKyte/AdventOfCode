package advent2019;

import java.util.Scanner;

public class IntcodeComputer implements Runnable {

    private int[] register;

    public IntcodeComputer(int[] register) {
        this.register = register;
    }

    @Override
    public void run() {
        int index = 0;
        boolean running = true;
        while (running) {
            //  Switch on opcode
            int opcode = register[index];
            int modes = 0;

            if (opcode > 99) {
                System.out.println("We got parameter modes: " + opcode);
                int actualOpCode = opcode % 100;
                System.out.println("Actual op code: " + actualOpCode);
                modes = opcode / 100;
                System.out.println("Remainder: " + modes);
                opcode = actualOpCode;

//                System.exit(0);
            }

            switch (opcode) {

                case 1:
                    //  OPCODE 1 -- ADD, 3 args
                    System.out.println("1 - add");
                    int addArg1 = register[index + 1];
                    int addArg2 = register[index + 2];
                    int addDest = register[index + 3];
                    register[addDest] = register[addArg1] + register[addArg2];
                    index += 4;
                    continue;

                case 2:
                    //  OPCODE 2 -- MULTIPLY, 3 args
                    System.out.println("2 - multiply");
                    System.out.println("modes: " + modes);
                    
                    int multArg1 = register[index + 1];
                    int multArg2 = register[index + 2];
                    int multDest = register[index + 3];
                    register[multDest] = register[multArg1] * register[multArg2];
                    index += 4;
                    System.exit(0);
                    continue;

                case 3:
                    //  OPCODE 3 -- SAVE INPUT, 1 arg
                    System.out.println("3 - input");
                    int saveDest = register[index + 1];
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Papers, please: ");
                    int input = Integer.parseInt(scanner.nextLine());
                    scanner.close();
                    register[saveDest] = input;
                    index += 2;
                    continue;

                case 4:
                    //  OPCODE 4 -- OUTPUT, 1 arg
                    System.out.println("4 - output");
                    int outIndex = register[index + 1];
                    System.out.println("Value at index " + outIndex + ": " + register[outIndex]);
                    index += 2;
                    continue;

                case 99:
                    //  OPCODE 99 -- END PROGRAM
                    System.out.println("99 - end program");
                    running = false;
                    continue;

                default:
                    System.out.println("Unknown opcode: " + register[index]);
                    System.exit(0);
            }
        }
    }

    public int valueAtIndex(int index) {
        return register[index];
    }

}
