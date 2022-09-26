package CS4488.Capstone.Library.Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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

    // SINGLETON instance, private constructor, and getInstance.
    private static FileManager instance;
    private FileManager(){}
    public FileManager getInstance(){
        return instance;
    }

    /**
     * Pass in a file path, return a boolean for  is readable or not.
     * @param pathString
     * @return is/is not readable;
     */
    public boolean checkFile(String pathString) {
        return Files.isReadable(Paths.get(pathString));
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
        }
        catch (IOException e){
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        return readIn;
    }

    /**
     * Saves a string to the specified file Path
     * @param FileContents
     * @param FilePath
     */
    public void saveStringToFile(String FileContents, String FilePath) {
       try {
            Files.writeString(Paths.get(FilePath), FileContents);
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }

    }


}
