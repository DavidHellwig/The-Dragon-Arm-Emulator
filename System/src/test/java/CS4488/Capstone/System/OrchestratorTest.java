package CS4488.Capstone.System;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorTest {
    Orchestrator orchestrator = Orchestrator.getInstance();

    private static final String resources = "./ResourceDirectories";
    String realFile = resources + "/Example Code/Program 1, Hello Branch and Math.txt";
    String fakeFile = resources + "/Example Code/fhw4fhq2fhq4thq.txt";
    String badFile = resources + "/Example Code/Program X, Bad Program.txt";

    @Test
    void executionAndPrint(){
        orchestrator.translateAndLoad(realFile);
        System.out.println(orchestrator.getProgramState().printableProgramState());

        while (orchestrator.next()){
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

        orchestrator.translateAndLoad(realFile);
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