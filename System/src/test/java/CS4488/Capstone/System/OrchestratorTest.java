package CS4488.Capstone.System;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorTest {
    Orchestrator orchestrator = Orchestrator.getInstance();

    @Test
    void executionAndPrint(){
        String realFile = "./Example Code/Program1, Hello Branch and Math.txt";
        orchestrator.translateAndLoad(realFile);
        orchestrator.getProgramState().printProgramState();

        while (orchestrator.next()){
            orchestrator.getProgramState().printProgramState();
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