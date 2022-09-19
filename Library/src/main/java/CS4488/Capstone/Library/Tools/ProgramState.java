package CS4488.Capstone.Library.Tools;

import CS4488.Capstone.Library.BackEndSystemInterfaces.ProgramStateInterface;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * This class is the Emulator's program sate.
 *
 * This Class is a Double-ton, like a singleton pattern but with a main instance and a cloned instance.
 *
 * The primary instance is for operation by the executor.
 * The clone is not operated on, thus making it read only, any writing to it will do nothing.
 *
 * Note: the readableState is only copied when called upon,
 * if the main instance is used it will bypass the excess processing power.
 *
 */
public class ProgramState implements ProgramStateInterface {

    // Constants and Class instances
    private static final int REGISTER_COUNT = 16;
    private static ProgramState emulationState=null;
    private static ProgramState readableState=null;

    // Sub-class for tracking the program counter's history
    public class memorySpace {
        public Hex4digit value;
        public int memoryLocation;
        public memorySpace(){
            value = new Hex4digit();
            memoryLocation = 0;
        }
    }
    // INSTANCE VARIABLES
    // Registers
    public Hex4digit[] registers;
    // I/O
    public Hex4digit input, output;
    // Memory & State
    public ArrayList<memorySpace> pcHistory;
    public ArrayList<ArrayList<Hex4digit>> memoryStateHistory;

    // PRIVATE SINGLETON CONSTRUCTOR
    private ProgramState(){
        registers = new Hex4digit[REGISTER_COUNT];
        for (int i=0; i<REGISTER_COUNT; i++){
            registers[i] = new Hex4digit();
        }
        input = new Hex4digit();
        output = new Hex4digit();
        pcHistory = new ArrayList<>();
        memoryStateHistory = new ArrayList<>();
    }

    // SINGLETON INSTANCE GETTER
    public ProgramState getInstance(){
        if (emulationState == null){
            emulationState = new ProgramState();
        }
        return emulationState;
    }

    // DOUBLETON READ-ONLY CLONE GETTER
    public ProgramState getReadableState(){
        if (readableState == null){
            readableState = new ProgramState();
        }
        cloneState();
        return readableState;
    }
    // read only clone function.
    private void cloneState(){
        // copy the register values
        for (int i=0; i<REGISTER_COUNT; i++){
            readableState.registers[i].setValue(emulationState.registers[i].getHexChars());
        }
        // copy i/o
        readableState.input.setValue(emulationState.input.getHexChars());
        readableState.output.setValue(emulationState.output.getHexChars());
        // copy the list of memory spaces
        readableState.pcHistory.clear();
        for (memorySpace m: emulationState.pcHistory){
            memorySpace toAdd = new memorySpace();
            toAdd.value.setValue(m.value.getHexChars());
            toAdd.memoryLocation = m.memoryLocation;
            readableState.pcHistory.add(toAdd);
        }
        // copy the list of lists of program memory.
        memoryStateHistory.clear();
        for (ArrayList<Hex4digit> arrayHex : emulationState.memoryStateHistory){
            ArrayList<Hex4digit> listToAdd = new ArrayList<>();
            for (Hex4digit h : arrayHex){
                Hex4digit hexToAdd = new Hex4digit();
                hexToAdd.setValue(h.getHexChars());
            }
            memoryStateHistory.add(listToAdd);
        }
    }

    // Program State Interface
    @Override
    public boolean initializeState(ArrayList<Hex4digit> code) {
        memorySpace pc = new memorySpace();
        pc.memoryLocation = 0;
        pc.value.setValue(code.get(0).getHexChars());
        pcHistory.add(pc);
        memoryStateHistory.add(code);
        return true;
    }

    @Override
    public void clearProgramState() {
        for (int i=0; i<REGISTER_COUNT; i++){
            registers[i].setValue((short)0);
        }
        input.setValue((short)0);
        output.setValue((short)0);
        for (ArrayList<Hex4digit> h: memoryStateHistory) {h.clear();}
        memoryStateHistory.clear();
        pcHistory.clear();
    }

}
