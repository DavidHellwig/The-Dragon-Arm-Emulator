package CS4488.Capstone.Library.BackEndSystemInterfaces;

import CS4488.Capstone.Library.Tools.Hex4digit;

import java.util.ArrayList;

/**
 * The Methods that interaction with the Translator will require
 *
 * @version 1.0
 * @author Traae
 */
public interface TranslatorInterface {

    /***
     * This Method opens the file to be translated. Will need to make use of the FileManager.
     *
     *
     * @param path The String file describing the file path
     * @return success/fail
     */
    public boolean loadFile(String path) throws Exception;

    /***
     * This function closes out the file the translator has open and clears its data.
     * The file should be closed post-translation, but this is provides a safety mechanism.
     */
    public void clearFile();

    /***
     * Provides an initial analysis of if the file will translate.
     *
     * @return is/isn't going to translate.
     */
    public boolean isTranslatable();

    /***
     * This method translates the Arm to Hex-Machine code.
     *
     * @return Arraylist of Hex4d lines of code.
     */
    public ArrayList<Hex4digit> translateToMachine();

    /**
     * This method gets the description of what went wrong should a process fail.
     *
     * @return String message
     */
    public String getLastExceptionMessage();

}
