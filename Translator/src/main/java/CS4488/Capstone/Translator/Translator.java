package CS4488.Capstone.Translator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Translator implements TranslatorInterface{

    private String file;
    private boolean loaded;

    public Translator(String file) throws Exception {
        this.file = "";
        this.loaded = loadFile(file);
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean loadFile(String path) throws Exception {
         this.file = readFile(path);
        return !this.file.equals("");
    }

    @Override
    public void clearFile() {
        setFile("");
        setLoaded(false);
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
        //noComments.strip()

        //remove all white space .replaceAll("\\s", "")
        return noComments.split(";");

    }

    public ArrayList<String> convertToHex(String [] parsedFile){
        InstructionParser instructionParser = new InstructionParser();

        ArrayList<String> hexFile = new ArrayList<>();
        for(int i = 0; i< parsedFile.length; i++){
            //InstructionParser.parser
            for (Map.Entry<String, String> me :
                    instructionParser.getParser().entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                parsedFile[i] = parsedFile[i].replaceAll(key, value);
            }

            hexFile.add(parsedFile[i].trim());
        }

        return hexFile;
    }

    public static void main(String[] args) throws Exception {
        Translator translator = new Translator("Translator/src/main/java/CS4488/Capstone/Translator/armcode.txt");
        ArrayList<String> list = translator.convertToHex(translator.parseFile(translator.file));

        for(String file : list){
            System.out.println(file);
        }
//        System.out.println(translator.file);
    }
}
