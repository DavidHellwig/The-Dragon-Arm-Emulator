package CS4488.Capstone.System;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrchestratorTest {
    Orchestrator orchestrator = Orchestrator.getInstance();

    private static final String resources = "./ResourceDirectories";
    String Program1 = resources + "/Example Code/Program 1, Hello Branch and Math.txt";
    String ProgramXYZ = resources + "/Example Code/Program XYZ, TestingCoverage.txt";
    String fakeFile = resources + "/Example Code/fhw4fhq2fhq4thq.txt";
    String badFile = resources + "/Example Code/Program X, Bad Program.txt";

    @Test
    @DisplayName("Execution and Print")
    void executionAndPrint() {
        int i = 0;
        orchestrator.translateAndLoad(Program1);
        System.out.println("Initial State, State " + i + ": \n");
        System.out.println(orchestrator.getProgramState().printableProgramState());

        while (orchestrator.next()) {
            i++;
            System.out.println("\nState " + i + ": \n");
            System.out.println(orchestrator.getProgramState().printableProgramState());
        }
        orchestrator.clearProgram();

        i = 0;
        System.out.println("Initial State, State " + i + ": \n");
        orchestrator.translateAndLoad(ProgramXYZ);
        orchestrator.getProgramState().input = new Hex4digit("002b");
        System.out.println(orchestrator.getProgramState().printableProgramState());
        while (orchestrator.next()) {
            i++;
            System.out.println("\nState " + i + ": \n");
            System.out.println(orchestrator.getProgramState().printableProgramState());
        }
        orchestrator.clearProgram();

    }

    @Test
    void next() {

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

        orchestrator.translateAndLoad(Program1);
        System.out.println("Translate and Load output:");
        System.out.println(orchestrator.getProgramState().printableProgramState());
    }

    @Test
    void convertToHexChars() {
    }

    @Test
    void convertToShort() {
    }
}