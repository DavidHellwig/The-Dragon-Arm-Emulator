package CS4488.Capstone.Library.FacadeInterfaces;

import java.util.ArrayList;

public interface ProgramStateAccess {
    public short[] getRegistersValues();
    public short getR0();
    public short getR1();
    public short getR2();
    public short getR3();
    public short getR4();
    public short getR5();
    public short getR6();
    public short getR7();
    public short getR8();
    public short getR9();
    public short getRa();
    public short getFp();
    public short getIp();
    public short getSp();
    public short getLr();
    public short getPc();
    public void enterInput(Short input);
    public Short getOutput();
    public short[] getBaseMemoryValues();
    public short[] getCurrentMemoryValues();
    public ArrayList<short[]> getMemoryStateGrid();
}
