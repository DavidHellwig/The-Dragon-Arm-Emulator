package CS4488.Capstone.Library.Tools;

import CS4488.Capstone.Library.BackEndSystemInterfaces.FileManagerInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;


/**
 * The File Manager
 *
 * Just the ability to check a file and read it in, and save it for now.
 * Extensible features later.
 *
 * @version 1.0
 * @author Traae
 */
public class FileManager {
    private static String defaultMessage = "No Current Error";
    private String lastErrorMessage;



    // SINGLETON instance, private constructor, and getInstance.
    private static FileManager instance = null;
    private FileManager(){
        lastErrorMessage = defaultMessage;
    }
    public static FileManager getInstance(){
        if (instance == null){
            instance = new FileManager();

        }
        return instance;
    }

    /**
     * Pass in a file path, return a boolean for  is readable or not.
     * @param pathString
     * @return is/is not readable;
     */
    public boolean checkFile(String pathString) {
        boolean result = Files.isReadable(Paths.get(pathString));
        lastErrorMessage = defaultMessage;
        return result;
    }

    /**
     * Returns the file contents in a string, automatically opens and closes the stream.
     * @param pathString
     * @return string of file contents;
     */
    public String fileToString(String pathString) {
        String readIn = "";
        try {
            readIn = Files.readString(Paths.get(pathString));
            lastErrorMessage = defaultMessage;
        }
        catch (IOException e){
            lastErrorMessage = ("FileManager.fileToString() Failure:\n" +
                    e.toString() + "\n" + e.getMessage());
        }
        return readIn;
    }

    /**
     * Saves a string to the specified file Path
     * @param FileContents
     * @param FilePath
     */
    public boolean saveStringToFile(String FileContents, String FilePath) {
       boolean result;
        result = Files.isWritable(Paths.get(FilePath));
        if (result){
            try {
                Files.writeString(Paths.get(FilePath), FileContents);
                lastErrorMessage = defaultMessage;
            }
            catch (Exception e){
                result = false;
                lastErrorMessage = ("FileManager.saveStringToFile() Failure:\n" +
                        e.toString() + "\n" + e.getMessage());
            }
        }
        return result;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
