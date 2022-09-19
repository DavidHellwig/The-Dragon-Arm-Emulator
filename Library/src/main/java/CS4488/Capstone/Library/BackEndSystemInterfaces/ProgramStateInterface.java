package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

import java.util.ArrayList;

public interface ProgramStateInterface {

    public boolean initializeState(ArrayList<Hex4digit> code);
    public void clearProgramState();

}
