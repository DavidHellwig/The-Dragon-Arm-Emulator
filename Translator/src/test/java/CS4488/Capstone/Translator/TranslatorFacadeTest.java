package CS4488.Capstone.Translator;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for the facade translator.
 *  *  * @version 0.0.9
 *  *  * @author Trae, Daniel Igbokwe
 *
 */
class TranslatorFacadeTest {

    private static final String resources = "./ResourceDirectories";
    String realFile = resources + "/Example Code/Program 1, Hello Branch and Math.txt";
    String fakeFile = resources + "/Example Code/fhw4fhq2fhq4thq.txt";
    String badFile = resources + "/Example Code/Program X, Bad Program.txt";
    String translationTest = resources + "/translationTester.txt";

    TranslatorFacade translatorFacade = new TranslatorFacade();



    @Test
    @DisplayName("Test for loading files")
    void loadFile() {
        try{
            boolean loaded = translatorFacade.loadFile(realFile);
            System.out.println(loaded);
            System.out.println(translatorFacade.getLastExceptionMessage());
            assertTrue(loaded);

            loaded = translatorFacade.loadFile(fakeFile);
            assertFalse(loaded);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    void isTranslatable() {
        boolean result = false;
        try{
            translatorFacade.loadFile(realFile);
            result = translatorFacade.isTranslatable();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertTrue(result);

    }

    @Test
    @DisplayName("Test for Translation to Machine code")
    void translateToMachine() {
        boolean result = false;

        ArrayList<Hex4digit> expectation = new ArrayList<>();
        //Adding the individual hand translated lines of the file listed in the realFile string;
        expectation.add(new Hex4digit("9020"));
        expectation.add(new Hex4digit("0002"));
        expectation.add(new Hex4digit("1010"));
        expectation.add(new Hex4digit("1011"));
        expectation.add(new Hex4digit("3012"));
        expectation.add(new Hex4digit("0000"));

        result = translateAndCompare(expectation, realFile);
        assertTrue(result);

        expectation.clear();
        expectation.add(new Hex4digit("0000"));
        expectation.add(new Hex4digit("1ff1"));
        expectation.add(new Hex4digit("2ff1"));
        expectation.add(new Hex4digit("3123"));
        expectation.add(new Hex4digit("4123"));
        expectation.add(new Hex4digit("5123"));
        expectation.add(new Hex4digit("6123"));
        expectation.add(new Hex4digit("7ff1"));
        expectation.add(new Hex4digit("8ff1"));
        expectation.add(new Hex4digit("9ff0"));
        expectation.add(new Hex4digit("a1ff"));
        expectation.add(new Hex4digit("b1ff"));
        expectation.add(new Hex4digit("c1ff"));
        expectation.add(new Hex4digit("d100"));
        expectation.add(new Hex4digit("e500"));
        expectation.add(new Hex4digit("0000"));



        result = translateAndCompare(expectation, translationTest);
        assertTrue(result);
    }


    @Test
    @DisplayName("Test Translation. Expected vs Output")

    private boolean translateAndCompare(ArrayList<Hex4digit> expectation, String testingFile){
        translatorFacade.loadFile(testingFile);
        ArrayList<Hex4digit> results = translatorFacade.translateToMachine();
        int size = expectation.size();
        boolean isSame = true;
        System.out.println("Expectation --- Result");
        String expect, result;
        for (int i = 0; i < size; i++){
            expect = expectation.get(i).getString();
            result = results.get(i).getString();
            System.out.println(expect + " --- " + result);

            if(isSame) {isSame = (expect.equals(result));}

        }
        return isSame;
    }


    @Test
    @DisplayName("Testing clearing file")
    void clearFile(){
       try{
           translatorFacade.loadFile(realFile);
           translatorFacade.clearFile();
           assertNull(translatorFacade.translateToMachine());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
    }

    @Test
    @DisplayName("Testing last exception message")
    void lastExceptionMessage() {
        try{
            translatorFacade.loadFile(realFile);
            String exceptionMessage = translatorFacade.getLastExceptionMessage();
            assertNotEquals(exceptionMessage, "");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




}