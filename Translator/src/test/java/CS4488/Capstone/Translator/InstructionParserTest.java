package CS4488.Capstone.Translator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    InstructionParser hashParser;
    @BeforeEach
    void init() {
        if (hashParser == null) {
            hashParser = InstructionParser.getInstance();
        }
    }

    @Test
    @DisplayName("Test Hash Map size")
    void getInstance() {
        // size of all instructions {registers, memory address, special register code}
        assertEquals(hashParser.getParser().size(), 292);
    }

    @Test
    @DisplayName("Test loading instructions")
    void loadInstructions() {
        assertEquals(hashParser.getParser().get("hlt"), "0");
        assertEquals(hashParser.getParser().get("ld"), "1");
        assertEquals(hashParser.getParser().get("st"), "2");
        assertEquals(hashParser.getParser().get("add"), "3");
        assertEquals(hashParser.getParser().get("sub"), "4");
        assertEquals(hashParser.getParser().get("mul"), "5");
        assertEquals(hashParser.getParser().get("div"), "6");
        assertEquals(hashParser.getParser().get("ldi"), "7");
        assertEquals(hashParser.getParser().get("sti"), "8");
        assertEquals(hashParser.getParser().get("br"), "9");
        assertEquals(hashParser.getParser().get("brz"), "a");
        assertEquals(hashParser.getParser().get("brn"), "b");
        assertEquals(hashParser.getParser().get("brp"), "c");
        assertEquals(hashParser.getParser().get("rd"), "d");
        assertEquals(hashParser.getParser().get("wr"), "e");
        assertEquals(hashParser.getParser().get("ldc"), "f");
    }

    @Test
    @DisplayName("Test loading registers")
    void loadRegisters() {
        for(int i = 0; i < 16; i++){
            String hexI = Integer.toHexString(i);
            assertEquals(hashParser.getParser().get("r"+hexI), hexI);

        }
    }


    @Test
    @DisplayName("Test loading memory")
    void loadMemory(){
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                String hexI = Integer.toHexString(i);
                String hexJ = Integer.toHexString(j);

                assertEquals(hashParser.getParser().get("m"+hexI+ hexJ), hexI+ hexJ);

            }
        }
    }
}