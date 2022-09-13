package CS4488.Capstone.Library.BackEndSystemInterfaces;

public interface FileManagerInterface {

    public boolean isValidPath(String path);
    public boolean openTxtFile(String path);
    public boolean closeTxtFile();
    public boolean saveMachineCode(String path);

}
