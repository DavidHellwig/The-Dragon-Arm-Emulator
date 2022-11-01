package CS4488.Capstone.Library.Tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTests {
    FileManager file = FileManager.getInstance();
    String realFile = "./Example Code/Program1, Hello Branch and Math.txt";
    String fakeFile = "./Example Code/fhw4fhq2fhq4thq.txt";
    //String badFile = "./Example Code/Program X, Bad Program.txt";
    String badFile = "D:\\Code\\The-Drgaon-Arm-Emulator\\Example Code\\Program X, Bad Program.txt";
    void printMessageifFalse(boolean b){
        if (!b){
            System.out.println(file.getLastErrorMessage());
        }
    }

    @Test
    void checkFile() {
        boolean result = file.checkFile(realFile);
        printMessageifFalse(result);
        //assertTrue(result);

        result = file.checkFile(badFile);
        printMessageifFalse(result);
        //assertTrue(result);

        result = file.checkFile(fakeFile);
        printMessageifFalse(!result);
        //assertFalse(result);
    }

    @Test
    void fileToString() {
        String in = null;
        in = file.fileToString(realFile);
        System.out.println(in);
        assertNotNull(in);
    }

    @Test
    void saveStringToFile() {
        String testfile = "./Example Code/SavedTestFile.txt";

        boolean result = file.saveStringToFile("Yo, this is a test file", testfile);

        //file.checkFile(testfile);
        printMessageifFalse(result);
        //assertTrue(result);
    }
}