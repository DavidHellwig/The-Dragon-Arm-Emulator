package CS4488.Capstone.Executor;

import CS4488.Capstone.Library.BackEndSystemInterfaces.ExecutorInterface;
import CS4488.Capstone.Library.Tools.ProgramState;
import CS4488.Capstone.Library.Tools.Hex4digit;
import org.apache.commons.text.WordUtils;

public class ExecutorFacade implements ExecutorInterface {

    private final char[] temp = {'4', 'a', 'f', '3'};
    private final Hex4digit word = new Hex4digit(temp);

    public static void main(String[] args) {
        ExecutorFacade test = new ExecutorFacade();
        test.testing();
    }

    public void testing() {
        // pass in the char[4] hex value into the function instead of the
        // Hex4digit object so that the switch case is cleaner.
        determineInstruction(word.getHexChars());
    }

    // takes in a char[4] instruction and based on the first value
    // executes the corresponding instruction from the InstructionSet class
    private void determineInstruction(char[] inst) {
        switch (inst[0]) {
            case '0' -> InstructionSet.halt();
            case '1' -> InstructionSet.load(inst[1], inst[2]);
            case '2' -> InstructionSet.store(inst[2], inst[2]);
            case '3' -> InstructionSet.add(inst[1], inst[2], inst[3]);
            case '4' -> InstructionSet.subt(inst[1], inst[2], inst[3]);
            case '5' -> InstructionSet.mult(inst[1], inst[2], inst[3]);
            case '6' -> InstructionSet.intDivide(inst[1], inst[2], inst[3]);
            case '7' -> InstructionSet.loadIndirect(inst[1], inst[2]);
            case '8' -> InstructionSet.storeIndirect(inst[1], inst[2]);
            case '9' -> InstructionSet.branch(inst[1]);
            case 'a' -> InstructionSet.branchNeg(inst[1], inst[2]);
            case 'b' -> InstructionSet.branchPos(inst[1], inst[2]);
            case 'c' -> InstructionSet.loadConstant(inst[1]);
            case 'd' -> InstructionSet.readInt(inst[1]);
            case 'e' -> InstructionSet.writeInt(inst[1]);
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
