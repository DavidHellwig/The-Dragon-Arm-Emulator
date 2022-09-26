package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.BackEndSystemInterfaces.ExecutorInterface;
import CS4488.Capstone.Library.Tools.ProgramState;
import CS4488.Capstone.Library.Tools.Hex4digit;
import org.apache.commons.text.WordUtils;

public class ExecutorFacade implements ExecutorInterface {

    // should be 3AF3 Add register A and F and put it in 3
    private final Hex4digit word1 = new Hex4digit((short)15091);
    // should be 3237 : Add register 2 and 3 and put it in 7
    private final Hex4digit word2 = new Hex4digit((short)12855);

    public static void main(String[] args) {
        ExecutorFacade test = new ExecutorFacade();


        test.testing();
    }

    public void testing() {
        fillRegisters();
        // pass in the hex4digit object for testing instructions
        determineInstruction(word2);
        System.out.println(ProgramState.getInstance().registers[7].getShort());
    }

    private void fillRegisters() {
        ProgramState.getInstance().registers[0] = new Hex4digit((short)5438);
        ProgramState.getInstance().registers[1] = new Hex4digit((short)6);
        ProgramState.getInstance().registers[2] = new Hex4digit((short)34);
        ProgramState.getInstance().registers[3] = new Hex4digit((short)73);
        ProgramState.getInstance().registers[4] = new Hex4digit((short)153);
        ProgramState.getInstance().registers[5] = new Hex4digit((short)865);
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
            case '1' -> InstructionSet.load((short)inst.getMiddle2Value(), inst.getHexChars()[3]);
            case '2' -> InstructionSet.store((short)inst.getMiddle2Value(), inst.getHexChars()[3]);
            case '3' -> InstructionSet.add(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '4' -> InstructionSet.subt(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '5' -> InstructionSet.mult(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '6' -> InstructionSet.intDivide(inst.getHexChars()[1], inst.getHexChars()[2], inst.getHexChars()[3]);
            case '7' -> InstructionSet.loadIndirect((short)inst.getMiddle2Value(), inst.getHexChars()[3]);
            case '8' -> InstructionSet.storeIndirect((short)inst.getMiddle2Value(), inst.getHexChars()[3]);
            case '9' -> InstructionSet.branch((short)inst.getMiddle2Value());
            case 'a' -> InstructionSet.branchNeg(inst.getHexChars()[1], (short)inst.getLast2Value());
            case 'b' -> InstructionSet.branchPos(inst.getHexChars()[1], (short)inst.getLast2Value());
            case 'c' -> InstructionSet.loadConstant(inst.getHexChars()[1]);
            case 'd' -> InstructionSet.readInt(inst.getHexChars()[1]);
            case 'e' -> InstructionSet.writeInt(inst.getHexChars()[1]);
            case 'f' -> System.out.println("Nothing");
            default -> System.out.println("Throw an error and halt");
        }
    }

    @Override
    public void setProgramState(ProgramState state) {

    }

    @Override
    public boolean hasState() {
        return false;
    }

    @Override
    public boolean next() {
        // Starting a new program, register 7 (the pc) should be
        // initialized to '0', or the first step in memory
        short currentStep = ProgramState.getInstance().registers[7].getShort();
        Hex4digit instruction = ProgramState.getInstance().pcHistory.get(currentStep).value;
        // figure out what the instruction is
        determineInstruction(instruction);

        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void clearState() {

    }

    @Override
    public String getLastExceptionMessage() {
        return null;
    }
}
