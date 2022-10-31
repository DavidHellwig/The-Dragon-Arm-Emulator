package CS4488.Capstone.System;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorTest {
    Orchestrator orchestrator = Orchestrator.getInstance();

    @Test void terminalTester() {
        boolean keepGoing = true;
        Scanner in = new Scanner(System.in);
        Orchestrator o = Orchestrator.getInstance();
        while (keepGoing) {
            System.out.println("Options:\n" +
                    "0. exit\n" +
                    "1. Translate and load Directory\n" +
                    "2. Next\n" +
                    "3. Set Input\n" +
                    "4. Clear Program\n");
            int a = in.nextInt();
            switch (a) {
                case 0:
                    keepGoing = false;
                    break;
                case 1:
                    String s = in.nextLine();
                    o.translateAndLoad(s);
                    o.getProgramState().printProgramState();
                    break;
                case 2:
                    o.next();
                    o.getProgramState().printProgramState();
                    break;
                case 3:
                    String hex = in.nextLine();
                    o.getProgramState().input = new Hex4digit(hex);
                    break;
                case 4:
                    o.clearProgram();
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    void next() {
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");
        System.out.println("YOOOOOOOOOOOOOOO\n");

    }

    @Test
    void getProgramState() {
    }

    @Test
    void sendInput() {
    }

    @Test
    void getOutput() {
    }

    @Test
    void translateAndLoad() {
        String realFile = "./ExampleCode/Program1, Hello Branch and Math.txt";
        orchestrator.translateAndLoad(realFile);

    }

    @Test
    void convertToHexChars() {
    }

    @Test
    void convertToShort() {
    }
}