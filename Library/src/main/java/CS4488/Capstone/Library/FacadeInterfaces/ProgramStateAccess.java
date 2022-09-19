package CS4488.Capstone.Library.FacadeInterfaces;
import CS4488.Capstone.Library.Tools.Hex4digit;
import CS4488.Capstone.Library.Tools.ProgramState;

import java.util.ArrayList;

/**
 * This interface dictates the method of access to the Program state by the UI
 *
 * @version 1.0
 * @author Traae
 */
public interface ProgramStateAccess {
    /**
     * This method provides a copy of the program state,
     * To ensure the actual state isn't accessible.
     *
     * @return a Copy Array Program State
     */
    public ProgramState getReadableCopy();

    public ProgramState getProgramState();

    /**
     * The Input must be passed into the backend ahead of it being 'read' by the emulation.
     *
     * @param input the input in the Input Box.
     */
    public void sendInput(char[] input);

    /**
     * get Output, simple as.
     * @return digits of Hex characters.
     */
    public char[] getOutput();
}
