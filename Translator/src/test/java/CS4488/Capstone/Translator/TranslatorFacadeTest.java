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

class TranslatorFacadeTest {

    private static final String resources = "./ResourceDirectories";
    String realFile = resources + "/Example Code/Program 1, Hello Branch and Math.txt";
    String fakeFile = resources + "/Example Code/fhw4fhq2fhq4thq.txt";
    String badFile = resources + "/Example Code/Program X, Bad Program.txt";

    TranslatorFacade translatorFacade = new TranslatorFacade();

    @Test
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
    void translateToMachine() {
        try{
            translatorFacade.loadFile(realFile);
            ArrayList<Hex4digit> expectation = new ArrayList<>();
            //Adding the individual hand translated lines of the file listed in the realFile string;
            expectation.add(new Hex4digit("9020"));
            expectation.add(new Hex4digit("0002"));
            expectation.add(new Hex4digit("1010"));
            expectation.add(new Hex4digit("1011"));
            expectation.add(new Hex4digit("3012"));
            expectation.add(new Hex4digit("0000"));

            ArrayList<Hex4digit> result = translatorFacade.translateToMachine();

            int size = expectation.size();
            boolean isSame = false;

            for (int i = 0; i < size; i++){
                isSame = (expectation.get(i).getValue() == result.get(i).getValue());
            }

            assertTrue(isSame);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    @Test
    @DisplayName("Test clear file")
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