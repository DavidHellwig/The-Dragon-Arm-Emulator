package CS4488.Capstone.Translator;

import java.util.ArrayList;

public interface TranslatorInterface {

    public boolean loadFile(String path) throws Exception;
    public void clearFile();
    public ArrayList<Short> translateToMachine();
    public boolean isTranslatable();
    
}
