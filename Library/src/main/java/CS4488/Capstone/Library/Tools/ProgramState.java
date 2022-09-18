package CS4488.Capstone.Library.Tools;

import CS4488.Capstone.Library.BackEndSystemInterfaces.ProgramStateInterface;

import java.util.ArrayList;

public class ProgramState implements ProgramStateInterface {
    // Registers
    Hex4digit r1, r2, r3, r4, r5, r6, r7, r8, r9, ra, fp, ip, sp, lr, pc;
    Hex4digit[] registers;
    // I/O
    Hex4digit input, output;
    // Memory
    ArrayList<ArrayList<Hex4digit>> memoryStates;

    public ProgramState(){
        r1 = new Hex4digit();
        r2 = new Hex4digit();
        r3 = new Hex4digit();
        r4 = new Hex4digit();
        r5 = new Hex4digit();
        r6 = new Hex4digit();
        r7 = new Hex4digit();
        r8 = new Hex4digit();
        r9 = new Hex4digit();
        ra = new Hex4digit();
        fp = new Hex4digit();
        ip = new Hex4digit();
        sp = new Hex4digit();
        lr = new Hex4digit();
        pc = new Hex4digit();
        input = new Hex4digit();
        output = new Hex4digit();
        memoryStates = new ArrayList<>();

    }

}
