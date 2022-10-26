package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class InstructionSetTests {

    @BeforeEach
    public void setUp() {
        // Fill the memory with a program designed for testing
        ArrayList<Hex4digit> program = new ArrayList<>();
        program.add(1, new Hex4digit());
        program.add(2, new Hex4digit());
        program.add(3, new Hex4digit());
        program.add(4, new Hex4digit());
        program.add(5, new Hex4digit());
        program.add(6, new Hex4digit());
        program.add(7, new Hex4digit());
        ProgramState.getInstance().memoryStateHistory.add(program);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    @DisplayName("test1")
    public void testHalt_1() throws Exception {

    }

    @Test
    @DisplayName("Test Load 1")
    public void testLoad_1() throws Exception {

    }

}
