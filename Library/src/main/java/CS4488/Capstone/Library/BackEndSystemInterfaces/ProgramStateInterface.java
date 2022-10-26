package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

import java.util.ArrayList;

/**
 * The methods needed for the Program state.
 *
 * NOTE :Because the State is mostly a data-class This became somewhat redundant in draft 2 of design.
 * TODO Draft 3 of design on Sprint 3, rework this.
 *
 * @version 1.0
 * @author Traae
 */
public interface ProgramStateInterface {

    public boolean initializeState(ArrayList<Hex4digit> code);
    public void clearProgramState();

}
