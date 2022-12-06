package CS4488.Capstone.Library.Tools;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTests {
    private static final String resources = "./ResourceDirectories";
    String realFile = resources + "/Example Code/Program 1, Hello Branch and Math.txt";
    String fakeFile = resources + "/Example Code/fhw4fhq2fhq4thq.txt";
    String badFile = resources + "/Example Code/Program X, Bad Program.txt";

    FileManager file = FileManager.getInstance();

    void printMessageifFalse(boolean b, String Label){
        if (!b){
            System.out.println(Label + ": \n" +file.getLastErrorMessage());
        }
    }



    @Test
    void checkFile() {
        boolean result;

        result = file.checkFile(realFile);
        printMessageifFalse(result, "Real File");
        assertTrue(result);

        result = file.checkFile(badFile);
        printMessageifFalse(result, "Bad File");
        assertTrue(result);

        result = file.checkFile(fakeFile);
        //printMessageifFalse(result, "Fake File");
        assertFalse(result);
    }

    @Test
    void fileToString() {
        String in = null;
        in = file.fileToString(realFile);
        //System.out.println(in);
        assertNotNull(in);
    }

//    @Test
//    void saveStringToFile() {
//        String testfile = "./Example Code/SavedTestFile.txt";
//
//        boolean result = file.saveStringToFile("Yo, this is a test file", testfile);
//
//        printMessageifFalse(result, "Test File is save String");
//        assertTrue(result);
//    }
}