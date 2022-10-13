package CS4488.Capstone.Translator;



import CS4488.Capstone.Library.Tools.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Translator  {

    private String armFile;
    private boolean loaded;
    private ArrayList<Hex4digit> translatedCode;
    private static Translator singleton = null;

    private Translator(String armFile) throws Exception {

        // Temp logic likely to change in the future release
        // load file
        this.setArmFile(this.readFile(armFile));
        this.setLoaded(!this.getArmFile().isEmpty());

        // translate if loaded
        if(this.isLoaded()){
            this.setTranslatedCode(this.convertToHex(this.parseFile(this.armFile)));
        }
    }

    public static Translator getInstance(String armFile) throws Exception {
        if(singleton == null){
            singleton = new Translator(armFile);
        }
        return singleton;
    }


    private void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void setArmFile(String armFile) {
        this.armFile = armFile;
    }

    public void setTranslatedCode(ArrayList<Hex4digit> translatedCode) {
        this.translatedCode = translatedCode;
    }

    public String getArmFile() {
        return this.armFile;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public ArrayList<Hex4digit> getTranslatedCode() {
        return this.translatedCode;
    }


    public void clearFile() {
        setArmFile("");
        setLoaded(false);
        setTranslatedCode(new ArrayList<>());
    }



    public String getLastExceptionMessage() {
        return null;
    }


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

    private String [] parseFile(String armFile){
        String noComments = armFile.replaceAll("@[a-zA-z0-9 ]+@", "");
        //noComments.strip()

        //remove all white space .replaceAll("\\s", "")
        return noComments.split(";");

    }

    private void setLabels(String lineOfCode, String [] parsedFile, int lineIndex){

    }



    private ArrayList<Hex4digit> convertToHex(String [] parsedFile) throws Exception {
        InstructionParser instructionParser =  InstructionParser.getInstance();

        ArrayList<Hex4digit> translatedFile = new ArrayList<>();
        int lineIndex = 0;
        for (String line : parsedFile) {

            //NOTE: Try iterating through split(" ") string not dictionary
            //System.out.println(parsedFile[i].trim());
            StringBuilder builder = new StringBuilder();

            for (String elem : line.trim().split(" ")) {
                String instruction = "";
                // check if label
                if(elem.equals("label")){
                    //pass current string line, string array, currentLine count
                    // in method that converts the labels
                    System.out.println("Label");
                    setLabels(line, parsedFile, lineIndex);
                }else{
                     instruction = instructionParser.getParser().get(elem);
                }

                if (instruction != null){
                    builder.append(instruction);
                }else{
                    builder.append(elem);

                }
//                System.out.println("SIZE: "+parsedFile[i].trim().split(" ").length);
//                System.out.println(elem);
//                System.out.println(instruction);
            }

            System.out.println("Translated instruct:"+builder);

            if (builder.length() > 4) {

                //change error message
                throw new Exception("Instruction memory overflow");

            } else if (builder.length() < 4) {
                // adds 0 until length == 4
                while (builder.length() < 4) {
                    builder.append("0");
                }
            }
            String lineOfCode = builder.toString();

            if (lineOfCode.matches("^[0-9a-f]+$")) {
                // create hex digit
                Hex4digit hex = new Hex4digit();
                hex.setValue(lineOfCode);
                translatedFile.add(hex);
            } else {
                throw new Exception("Instruction contains unknown characters.");
            }

            lineIndex++;
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
