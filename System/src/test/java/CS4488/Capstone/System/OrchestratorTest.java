package CS4488.Capstone.System;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorTest {
    Orchestrator orchestrator = Orchestrator.getInstance();

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