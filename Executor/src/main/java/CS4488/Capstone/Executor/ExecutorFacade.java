package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.BackEndSystemInterfaces.ExecutorInterface;
import CS4488.Capstone.Library.Tools.ProgramState;
import CS4488.Capstone.Library.Tools.Hex4digit;

public class ExecutorFacade implements ExecutorInterface {

    // Holds the index of what place the memory is at in the arraylist
    private int MEMORYSTATEINDEX = 0;

    // Updates the index of what stage the memory is in
    private void incrementMemoryIndex() {
        MEMORYSTATEINDEX += 1;
    }

    // takes in a hex4digit instruction and based on the first value
    // executes the corresponding instruction from the InstructionSet class
    //
    // There is an option to instead just pass in the entire hex4digit object
    // into the function and let the function itself separate the values it needs,
    // but it makes the functions cleaner if they instead are given the exact
    // values they need, so that there isn't a bunch of variable assignments in
    // all the instructionSet functions
    private void determineInstruction(Hex4digit inst) {
        switch (inst.getHexChars()[0]) {
            case '0' -> InstructionSet.halt();
            case '1' -> InstructionSet.load(inst.getMiddle2Value(), inst.getHexChars()[3], MEMORYSTATEINDEX);
            case '2' -> InstructionSet.store(inst.getMiddle2Value(), inst.getHexChars()[3], MEMORYSTATEINDEX);
            case '3' -> InstructionSet.add(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '4' -> InstructionSet.subt(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '5' -> InstructionSet.mult(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '6' -> InstructionSet.intDivide(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '7' -> InstructionSet.loadIndirect(inst.getMiddle2Value(), inst.getHexChars()[3], MEMORYSTATEINDEX);
            case '8' -> InstructionSet.storeIndirect(inst.getMiddle2Value(), inst.getHexChars()[3], MEMORYSTATEINDEX);
            case '9' -> InstructionSet.branch(inst.getMiddle2Value());
            case 'a' -> InstructionSet.branchNeg(inst.getHexChars()[1], inst.getLast2Value());
            case 'b' -> InstructionSet.branchPos(inst.getHexChars()[1], inst.getLast2Value());
            case 'c' -> InstructionSet.loadConstant(inst.getHexChars()[1]);
            case 'd' -> InstructionSet.readInt(inst.getHexChars()[1]);
            case 'e' -> InstructionSet.writeInt(inst.getHexChars()[1]);
            case 'f' -> System.out.println("Nothing");
            default -> System.out.println("Throw an error and halt");
        }
    }

    @Override
    public boolean next() {
        // First, check to see if a ProgramState has been instantiated
        if (!hasState()) {
            setProgramState(ProgramState.getInstance());
        }

        // Check to see if there is another instruction from the program Counter i.e. Register 15
        if (!hasNext()) {
            return false;
        }

        // the next instruction that is going to be executed is indexed by the program Counter.
        // so in register 15, the index in memory is held for that instruction. So if the value
        // of register 15 is 7, that means index 7 in the memoryStateHistory will be executed.
        // instruction = ArrayList[ ArrayList[ ProgramCounter ] ]
        Hex4digit instruction = ProgramState.getInstance().memoryStateHistory.get(MEMORYSTATEINDEX).get(ProgramState.getInstance().registers[15].getValue());
        // figure out what the instruction is and execute it
        determineInstruction(instruction);
        // The memory index then needs to be incremented since the list will be copied over 1
        incrementMemoryIndex();
        // Finally, the memory ArrayList needs to be copied over
        // to the next index in the list of lists itself
        ProgramState.getInstance().memoryStateHistory.add(MEMORYSTATEINDEX, ProgramState.getInstance().memoryStateHistory.get(MEMORYSTATEINDEX - 1));
        return true;
    }

    @Override
    public boolean hasNext() {
        // if the program counter contains a -1, the halt command was executed
        return ProgramState.getInstance().registers[15].getValue() != -1;
    }

    @Override
    public void setProgramState(ProgramState state) {
        state.memoryStateHistory.add(MEMORYSTATEINDEX, state.memoryStateHistory.get(MEMORYSTATEINDEX - 1));
    }

    @Override
    public boolean hasState() {
        return ProgramState.getInstance().memoryStateHistory.get(MEMORYSTATEINDEX).get(0) != null;
    }

    @Override
    public void clearState() {
        MEMORYSTATEINDEX = 0;
        ProgramState.getInstance().memoryStateHistory.clear();
    }

    @Override
    public String getLastExceptionMessage() {
        return null;
    }
}
