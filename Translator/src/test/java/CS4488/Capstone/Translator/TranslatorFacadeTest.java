package CS4488.Capstone.Translator;

import CS4488.Capstone.Library.Tools.Hex4digit;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorFacadeTest {
    TranslatorFacade translatorFacade = null;
    String realFile = "./ExampleCode/Program1, Hello Branch and Math.txt";
    String fakeFile = "./ExampleCode/fhw4fhq2fhq4thq.txt";
    String badFile = "./Example Code/Program X, Bad Program.txt";

    void init(){
        if (translatorFacade == null){
            translatorFacade = new TranslatorFacade();
        }
    }

    @Test
    void loadFile() throws Exception {
        init();

        boolean loaded = translatorFacade.loadFile(realFile);
        assertTrue(loaded);

        loaded = translatorFacade.loadFile(fakeFile);
        assertFalse(loaded);

    }

    @Test
    void isTranslatable() throws Exception {
        init();
        boolean result = false;
        translatorFacade.loadFile(realFile);
        result = translatorFacade.isTranslatable();
        assertTrue(result);
        result = translatorFacade.loadFile(badFile);
        assertFalse(result);

    }

    @Test
    void translateToMachine() {
        init();
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

    }

}