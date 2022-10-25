package CS4488.Capstone.Library.Tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTests {
    FileManager file = FileManager.getInstance();
    String realFile = "./ExampleCode/Program1, Hello Branch and Math.txt";
    String fakeFile = "./ExampleCode/fhw4fhq2fhq4thq.txt";
    String badFile = "./Example Code/Program X, Bad Program.txt";

    @Test
    void checkFile() {
<<<<<<< HEAD
        assertTrue(file.checkFile(realFile));
        assertTrue((file.checkFile(badFile)));
        assertFalse(file.checkFile(fakeFile));
=======

>>>>>>> feature/integration1
    }

    @Test
    void fileToString() {
    }

    @Test
    void saveStringToFile() {
    }
}