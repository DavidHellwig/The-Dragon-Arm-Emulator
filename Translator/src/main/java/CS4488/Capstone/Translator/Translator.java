package CS4488.Capstone.Translator;



import CS4488.Capstone.Library.Tools.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class Translator  {

    private String armFile;
    private boolean loaded;
    private ArrayList<Hex4digit> translatedCode;

    public Translator(String armFile) throws Exception {
        // Temp logic likely to change in the future release
        this.armFile = readFile(armFile);
        this.loaded = this.armFile.equals("") ;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setArmFile(String armFile) {
        this.armFile = armFile;
    }

    public void setTranslatedCode(ArrayList<Hex4digit> translatedCode) {
        this.translatedCode = translatedCode;
    }

    public String getArmFile() {
        return armFile;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public ArrayList<Hex4digit> getTranslatedCode() {
        return translatedCode;
    }

   // @Override
    public boolean loadFile(String path) {
        //this.file = readFile(path);
        return this.armFile.equals("");
    }

   //@Override
    public void clearFile() {
        setArmFile("");
        setLoaded(false);
        setTranslatedCode(null);
    }

   // @Override
    public ArrayList<Hex4digit> translateToMachine() {
        return null;
    }

   //@Override
    public String getLastExceptionMessage() {
        return null;
    }

    //@Override
    public boolean isTranslatable() {
        return false;
    }

    private String readFile(String file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();

        while (reader.ready()) {
            text.append(reader.readLine());
        }

        return text.toString().toLowerCase();
    }

    public String [] parseFile(String armFile){
        String noComments = armFile.replaceAll("@[a-zA-z0-9 ]+@", "").replaceAll("\\s", "");
        //noComments.strip()

        //remove all white space .replaceAll("\\s", "")
        return noComments.split(";");

    }

    public ArrayList<Hex4digit> convertToHex(String [] parsedFile){
        InstructionParser instructionParser =  InstructionParser.getInstance();

        ArrayList<String> hexFile = new ArrayList<>();
        ArrayList<Hex4digit> translatedFile = new ArrayList<>();
        for(int i = 0; i< parsedFile.length; i++){

            for (Map.Entry<String, String> me :
                    instructionParser.getParser().entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                parsedFile[i] = parsedFile[i].replaceAll(key, value);
            }

            // create hex digit
            Hex4digit hex = new Hex4digit();
            hex.setValue(parsedFile[i].trim());
            translatedFile.add(hex);

            hexFile.add(parsedFile[i].trim());

        }

        this.translatedCode = translatedFile;
        return this.translatedCode;
    }

    public static void main(String[] args) throws Exception {
        Translator translator = new Translator("Translator/src/main/java/CS4488/Capstone/Translator/armcode.txt");

         ArrayList<Hex4digit> translatedCode = translator.convertToHex(translator.parseFile(translator.armFile));


        for(Hex4digit code : translatedCode){
            System.out.println(code.getHexChars());
        }
//        System.out.println(translator.file);
    }
}
