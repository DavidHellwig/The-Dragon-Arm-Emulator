package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

import java.util.ArrayList;

public interface ProgramStateInterface {

    public boolean initializeState(ArrayList<Hex4digit> code);
    public void clearProgramState();
    public ArrayList<Hex4digit> registerIOValues();
    public Hex4digit getR0();
    public Hex4digit getR1();
    public Hex4digit getR2();
    public Hex4digit getR3();
    public Hex4digit getR4();
    public Hex4digit getR5();
    public Hex4digit getR6();
    public Hex4digit getR7();
    public Hex4digit getR8();
    public Hex4digit getR9();
    public Hex4digit getRa();
    public Hex4digit getFp();
    public Hex4digit getIp();
    public Hex4digit getSp();
    public Hex4digit getLr();
    public Hex4digit getPc();
    public void enterInput(Hex4digit input);
    public Hex4digit getOutput();
    public Hex4digit[] getBaseMemoryValues();
    public Hex4digit[] getCurrentMemoryValues();
    public ArrayList<Hex4digit> getMemoryStateGrid();
    public void setR0(Hex4digit r);
    public void setR1(Hex4digit r);
    public void setR2(Hex4digit r);
    public void setR3(Hex4digit r);
    public void setR4(Hex4digit r);
    public void setR5(Hex4digit r);
    public void setR6(Hex4digit r);
    public void setR7(Hex4digit r);
    public void setR8(Hex4digit r);
    public void setR9(Hex4digit r);
    public void setRa(Hex4digit r);
    public void setFp(Hex4digit r);
    public void setIp(Hex4digit r);
    public void setSp(Hex4digit r);
    public void setLr(Hex4digit r);
    public void setPc(Hex4digit r);



}
