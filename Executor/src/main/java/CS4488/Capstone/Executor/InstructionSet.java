package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;

public final class InstructionSet {

    // The program counter is the register (r15) that stores the
    // memory address of the next instruction to be executed. It
    // needs to be incremented after an instruction to go the
    // next memory address where an instruction is held.
    public static void incrementProgramCounter() {
        System.out.println("Increment Program Counter");
        // This should simply be moving the index in the memory ArrayList. so
        // if you are executing the instruction at position 5 in the list, the
        // program counter will increment to position 6 next. This is not the
        // same as the actual memory address being incremented by 1. They are
        // independent in the emulation.
        ProgramState.getInstance().registers[15].setValue( (short)(ProgramState.getInstance().registers[15].getValue() + 1));
    }

    // putting a -1 into the program counter lets the executor know
    // that the program is finished when reading from register 15
    public static void halt() {
        System.out.println("Halt");
        ProgramState.getInstance().registers[15].setValue((short)-1);
    }

    // finds a memory location from the program state and takes its Hex4digit
    // value and sets it to the specified register's value.
    public static void load(short mem, char reg, int index) {
        System.out.println("Load");
        ProgramState state = ProgramState.getInstance();
        // Get the hex4digit object from the specified location in memory
        Hex4digit value = state.memoryStateHistory.get(index).get(mem);
        // put that hex4digit value into the specified register
        state.registers[Hex4digit.hexValue(reg)].setValue(value.getHexChars());
        incrementProgramCounter();
    }

    // get the specified memory index in the memory state and set its
    // Hex4digit value to that of the value in the specified register
    public static void store(short mem, char reg, int index) {
        System.out.println("Store");
        ProgramState state = ProgramState.getInstance();
        // get the Hex4digit object from the specified register
        Hex4digit value = state.registers[Hex4digit.hexValue(reg)];
        // set the memory spot specified to the value taken from the register
        state.memoryStateHistory.get(index).get(mem).setValue(value.getHexChars());
        incrementProgramCounter();
    }

    // add values of register 1 and register 2 and stores the result in register 3.
    // function converts the characters from hex to shorts then converts the result
    // back into hex to store in the resulting register.
    public static void add(char reg1, char reg2, char reg3) {
        System.out.println("Add");
        ProgramState state = ProgramState.getInstance();
        // add the values from the 2 given registers. Must be converted to shorts to do the addition
        short result = (short) (state.registers[Hex4digit.hexValue(reg1)].getValue() + state.registers[Hex4digit.hexValue(reg2)].getValue());
        // set the result to the Hex4digit's value in the third given register
        state.registers[Hex4digit.hexValue(reg3)].setValue(result);
        incrementProgramCounter();
    }

    // subtract value within first register by the value within the second register
    // and store the result in the third register.
    // function converts the characters from hex to shorts then converts the result
    // back into hex to store in the resulting register
    public static void subt(char reg1, char reg2, char reg3) {
        System.out.println("Subt");
        ProgramState state = ProgramState.getInstance();
        // subtract value from first register by the second register
        short result = (short) (state.registers[Hex4digit.hexValue(reg1)].getValue() - state.registers[Hex4digit.hexValue(reg2)].getValue());
        // set the result to the Hex4digit's value in the third given register
        state.registers[Hex4digit.hexValue(reg3)].setValue(result);
        incrementProgramCounter();
    }

    // multiply the values from within first register and second register and store them
    // within third register.
    // function converts the characters from hex to shorts then converts the result
    // after multiplying back into hex to store in the third register.
    public static void mult(char reg1, char reg2, char reg3) {
        System.out.println("Mult");
        ProgramState state = ProgramState.getInstance();
        // multiply values from first and second register together
        short result = (short) (state.registers[Hex4digit.hexValue(reg1)].getValue() * state.registers[Hex4digit.hexValue(reg2)].getValue());
        // store the result in the third given register
        state.registers[Hex4digit.hexValue(reg3)].setValue(result);
        incrementProgramCounter();
    }

    // divides the value from register 1 by the value in register 2,
    // then stores the result in register 3
    public static void intDivide(char reg1, char reg2, char reg3) {
        System.out.println("IntDivide");
        ProgramState state = ProgramState.getInstance();
        short result = (short) (state.registers[Hex4digit.hexValue(reg1)].getValue() / state.registers[Hex4digit.hexValue(reg2)].getValue());
        state.registers[Hex4digit.hexValue(reg3)].setValue(result);
        incrementProgramCounter();
    }

    public static void loadIndirect(short mem, char reg, int index) {
        System.out.println("LoadIndirect");
        ProgramState state = ProgramState.getInstance();
        Hex4digit address = state.memoryStateHistory.get(index).get(mem);
        Hex4digit value = state.memoryStateHistory.get(index).get(address.getMiddle2Value());
        state.registers[Hex4digit.hexChar(reg)].setValue(value.getHexChars());
    }

    public static void storeIndirect(short mem, char reg, int index) {
        System.out.println("StoreIndirect");
        ProgramState state = ProgramState.getInstance();
        // grab the hex4digit object that is in the specified register
        // This will hold the address for the indirect memory location
        Hex4digit address = state.registers[Hex4digit.hexValue(reg)];
        // Then, go to that memory location and take its hex4digit object
        // this is the actual value we are storing in the specified memory
        Hex4digit value = state.memoryStateHistory.get(index).get(address.getMiddle2Value());
        // Now store the value into memory
        state.memoryStateHistory.get(index).get(mem).setValue(value.getHexChars());
        incrementProgramCounter();
    }

    // Change the program counter to be the specified memory index
    public static void branch(short mem) {
        System.out.println("Branch");
        ProgramState.getInstance().registers[15].setValue(mem);
    }

    // Changes the program counter to the specified memory index if
    // the value in the specified register is negative
    public static void branchNeg(char reg, short mem) {
        System.out.println("BranchNeg");
        if (ProgramState.getInstance().registers[Hex4digit.hexValue(reg)].getValue() < 0) {
            ProgramState.getInstance().registers[15].setValue(mem);
        }
    }

    // Changes the program counter to the specified memory index if
    // the value in the specified register is positive
    public static void branchPos(char reg, short mem) {
        System.out.println("BranchPos");
        if (ProgramState.getInstance().registers[Hex4digit.hexValue(reg)].getValue() >= 0) {
            ProgramState.getInstance().registers[15].setValue(mem);
        }
    }

    public static void loadConstant(char reg) {
        System.out.println("LoadConstant");
    }

    // Takes a hex4d from the output of the program state and assigns that
    // value into the specified register location
    public static void readInt(char reg) {
        System.out.println("ReadInt");
        ProgramState.getInstance().registers[Hex4digit.hexValue(reg)].setValue(ProgramState.getInstance().output.getHexChars());
        incrementProgramCounter();
    }

    // Takes a hex4d from a specified register and assigns that value
    // to the program state's input
    public static void writeInt(char reg) {
        System.out.println("WriteInt");
        ProgramState.getInstance().input.setValue(ProgramState.getInstance().registers[Hex4digit.hexValue(reg)].getHexChars());
        incrementProgramCounter();
    }

}
