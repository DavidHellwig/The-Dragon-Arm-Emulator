package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.Hex4d;

import java.util.ArrayList;

public interface ProgramStateInterface {

    public boolean initializeState(ArrayList<Hex4d> code);
    public void clearProgramState();
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
    public void setR0();
    public void setR1();
    public void setR2();
    public void setR3();
    public void setR4();
    public void setR5();
    public void setR6();
    public void setR7();
    public void setR8();
    public void setR9();
    public void setRa();
    public void setFp();
    public void setIp();
    public void setSp();
    public void setLr();
    public void setPc();



}
