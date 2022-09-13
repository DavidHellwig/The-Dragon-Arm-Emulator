package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.ProgramState;

public interface ExecutorInterface {

    /***
     * Passes in a reference to the ProgramState to be manipulated.
     * @param state
     */
    public void setProgramState(ProgramState state);

    /***
     * Is a state currently set to be executed?
     * @return yes/no
     */
    public boolean hasState();

    /***
     * Execute the next instruction upon the Program State
     * @return success/fail
     */
    public boolean next();

    /***
     * Does the program have a next instruction to execute?
     * @return yes/no
     */
    public boolean hasNext();

    /**
     * removes the Program State from the Executor
     */
    public void clearState();

    /**
     * This method gets the description of what went wrong should a process fail.
     *
     * @return String message
     */
    public String getLastExceptionMessage();

}
