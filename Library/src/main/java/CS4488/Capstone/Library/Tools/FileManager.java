package CS4488.Capstone.Library.Tools;

import CS4488.Capstone.Library.BackEndSystemInterfaces.FileManagerInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public String readFile(String file) {
        StringBuilder text = new StringBuilder();

        try{

            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                text.append(reader.readLine());
            }
            lastErrorMessage = defaultMessage;

        }catch (IOException e){
            lastErrorMessage = "File Manager: in readFile()\n" + e.getMessage();
        }

        return text.toString().toLowerCase();
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

//    /**
//     * Saves a string to the specified file Path
//     * @param FileContents
//     * @param FilePath
//     */
//    public boolean saveStringToFile(String FileContents, String FilePath) {
//       boolean result;
//       System.out.println("IS WRITABLE?????");
//        result = true;//Files.isWritable(Paths.get(FilePath));
//        System.out.println(result);
//            try {
//                Files.writeString(Paths.get(FilePath), FileContents);
//                lastErrorMessage = defaultMessage;
//            }
//            catch (Exception e){
//                result = false;
//                lastErrorMessage = ("FileManager.saveStringToFile() Failure:\n" +
//                        e.toString() + "\n" + e.getMessage());
//            }
//        return result;
//    }
}
