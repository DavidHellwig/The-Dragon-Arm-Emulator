package CS4488.Capstone.Library.FacadeInterfaces;
import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;

import java.util.ArrayList;

/**
 * This interface dictates the method of access to the Program state by the UI
 */
public interface ProgramStateAccess {
    /**
     * This method provides a copy of the program state,
     * To ensure the actual state isn't accessible.
     *
     * @return a Copy Array Program State
     */
    public ProgramState getProgramStateCopy();

    /**
     * The Input must be passed into the backend ahead of it being 'read' byt the emulation.
     *
     * @param in the input in the Input Box.
     */
    public void sendInput(Hex4digit in);
}
