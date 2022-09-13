package CS4488.Capstone.Translator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Translator implements TranslatorInterface{



    @Override
    public boolean loadFile(String path) throws Exception {
        String file = readFile(path);

        return !file.equals("");
    }

    @Override
    public void clearFile() {

    }

    @Override
    public ArrayList<Short> translateToMachine() {
        return null;
    }

    @Override
    public boolean isTranslatable() {
        return false;
    }

    private String readFile(String file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();

        while (reader.ready()) {
            text.append(reader.readLine()).append("\n");
        }

        return text.toString().toLowerCase();
    }

    public String [] parseFile(String armFile){
        String noComments = armFile.replaceAll("@[a-zA-z0-9 ]+@", "").replaceAll("\\s", "");

        //remove all white space .replaceAll("\\s", "")

        return noComments.split(";");

    }
}
