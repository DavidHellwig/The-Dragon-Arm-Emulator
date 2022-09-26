package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.Tools.Hex4digit;

public final class InstructionSet {

    // 16 registers with some random values in them
    public static int[] Registers = {4, 7, 19, 204, 219, 226, 9, 5, 17, 21, 36, 10, 11, 289, 304, 203};

    public static void halt() {
        System.out.println("Halt");
    }

    public static void load(char mem, char reg) {
        System.out.println("Load");
    }

    public static void store(char mem, char reg) {
        System.out.println("Store");
    }

    // TODO set up code to work with program state, not custom registers
    // TODO increment stack pointer or whatever
    // add values of register 1 and register 2 and stores the result in register 3.
    // function converts the characters from hex to integers which correspond to the
    // positions in the temporary registers array acting as memory.
    public static void add(char reg1, char reg2, char reg3) {
        System.out.println("Add");
        // add the values from the 2 given registers
        // store the result in the third given register
        Registers[Hex4digit.hexValue(reg3)] = Registers[Hex4digit.hexValue(reg1)] + Registers[Hex4digit.hexValue(reg2)];
    }

    // TODO set up code to work with program state, not custom registers
    // subtract value within first register by the value within the second register
    // and stores the result in the third register.
    // function converts the characters from hex to integers which correspond to the
    // positions in the temporary registers array acting as memory.
    public static void subt(char reg1, char reg2, char reg3) {
        System.out.println("Subt");
        // subtract value from first register by the second register
        // store the result in the position from third given register
        Registers[Hex4digit.hexValue(reg3)] = Registers[Hex4digit.hexValue(reg1)] - Registers[Hex4digit.hexValue(reg2)];
    }

    // TODO set up code to work with program state, not custom registers
    // multiply the values from within first register and second register and store them
    // within third register.
    // function converts the characters from hex to integers which correspond to the
    // positions in the temporary registers array acting as memory.
    public static void mult(char reg1, char reg2, char reg3) {
        System.out.println("Mult");
        Registers[Hex4digit.hexValue(reg3)] = Registers[Hex4digit.hexValue(reg1)] * Registers[Hex4digit.hexValue(reg2)];
    }

    public static void intDivide(char reg1, char reg2, char reg3) {
        System.out.println("IntDivide");
    }

    public static void loadIndirect(char mem, char reg) {
        System.out.println("LoadIndirect");
    }

    public static void storeIndirect(char mem, char reg) {
        System.out.println("StoreIndirect");
    }

    public static void branch(char mem) {
        System.out.println("Branch");
    }

    public static void branchNeg(char reg, char mem) {
        System.out.println("BranchNeg");
    }

    public static void branchPos(char reg, char mem) {
        System.out.println("BranchPos");
    }

    public static void loadConstant(char reg) {
        System.out.println("LoadConstant");
    }

    public static void readInt(char reg) {
        System.out.println("ReadInt");
    }

    public static void writeInt(char reg) {
        System.out.println("WriteInt");
    }

}
