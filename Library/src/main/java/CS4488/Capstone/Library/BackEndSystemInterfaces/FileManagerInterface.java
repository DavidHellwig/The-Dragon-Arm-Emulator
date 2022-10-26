package CS4488.Capstone.Library.BackEndSystemInterfaces;

/**
 * Everything out Classes should need to do with the File Manager
 *
 * @version 0.5
 * @author Traae
 */
public interface FileManagerInterface {

    public boolean isValidPath(String path);
    public boolean openTxtFile(String path);
    public boolean closeTxtFile();
    public boolean saveMachineCode(String path);

}
