package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

public class ExecutorFacadeTests {

    ExecutorFacade executor;

    @BeforeEach
    public void setUp() {
        // Fill the memory with values that will act as a program
        // The program should be as follows:
        //
        // br 3
        // #5
        // #8
        // ld 1 r0
        // ld 2 r1
        // add r0 r1 r2
        ArrayList<Hex4digit> program = new ArrayList<Hex4digit>();
        program.add(new Hex4digit(36912));
        program.add(new Hex4digit(5));
        program.add(new Hex4digit(8));
        program.add(new Hex4digit(4112));
        program.add(new Hex4digit(4129));
        program.add(new Hex4digit(12306));
        ProgramState.getInstance().memoryStateHistory.add(program);

        executor = new ExecutorFacade();
    }

    @AfterEach
    public void tearDown() {
        // clear the program state
        ProgramState.getInstance().memoryStateHistory.clear();
        executor = null;
    }

    @Test
    @DisplayName("Test Next 1")
    public void testNext_1() throws Exception {
        executor.next();
        // Program Counter should have branched to '3'
        //Assertions.assertEquals(3, ProgramState.getInstance().registers[15].getValue());
    }

}
