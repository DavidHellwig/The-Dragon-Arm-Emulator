package CS4488.Capstone.Library.FacadeInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

import java.util.ArrayList;

public interface ProgramStateAccess {
    public Hex4digit[] getRegistersValues();
    public Hex4digit getPriorPCValue();
    public void enterInput(Hex4digit input);
    public Hex4digit getOutput();
    public ArrayList<Hex4digit> getBaseMemoryValues();
    public ArrayList<Hex4digit> getCurrentMemoryValues();
    public ArrayList<Hex4digit[]> getMemoryStateGrid();
}
