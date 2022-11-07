package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class InstructionSetTests {

    ProgramState state = ProgramState.getInstance();

    @BeforeEach
    public void setUp() {
        // Fill the memory with a program designed for testing
        ArrayList<Hex4digit> program = new ArrayList<>();
        program.add(new Hex4digit("9030"));
        program.add(new Hex4digit("0005"));
        program.add(new Hex4digit("0008"));
        program.add(new Hex4digit("1010"));
        program.add(new Hex4digit("1021"));
        program.add(new Hex4digit("3012"));

        state.memoryStateHistory.add(program);
    }

    @AfterEach
    public void tearDown() {
        state.clearProgramState();
    }

    @Test
    @DisplayName("Test Halt")
    public void testHalt() throws Exception {
        InstructionSet.halt(state);
        Assertions.assertEquals(-1, state.registers[15].getValue());
    }

    @Test
    @DisplayName("Test Load 1")
    public void testLoad_1() throws Exception {
        // take the value in memory space 1 and load it into register 0
        InstructionSet.load(state, 1, '0', 0);
        // take the value in memory space 2 and load it into register 1
        InstructionSet.load(state, 2, '1', 0);
        Assertions.assertEquals(5, state.registers[0].getValue());
        Assertions.assertEquals(8, state.registers[1].getValue());
        // the program counter should have been incremented twice
        Assertions.assertEquals(2, state.registers[15].getValue());
    }

    @Test
    @DisplayName("Test Load 2")
    public void testLoad_2() throws Exception {

    }

    @Test
    @DisplayName("Test Load Indirect 1")
    public void testLoadIndirect_1() throws Exception {
        InstructionSet.loadIndirect(state, 1, '0', 0);
        Assertions.assertEquals(12306, state.registers[0].getValue());
    }

}
