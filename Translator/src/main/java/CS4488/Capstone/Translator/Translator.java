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

//        // translate if loaded
//        if(this.isLoaded()){
//            this.setTranslatedCode(this.convertToHex(this.parseFile(this.armFile)));
//        }
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

    private  String[] removeTheElement(String[] arr, int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        String[] anotherArray = new String[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
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
        String noComments = armFile.replaceAll("@[a-zA-z0-9 ]+@", "").replaceAll("#", "").replaceAll("0x", "");
        //noComments.strip()

        //remove all white space .replaceAll("\\s", "")
        //.replaceAll("0x", "")
        return noComments.split(";");

    }

    private void setLabels(String lineOfCode, String [] parsedFile, int lineIndex){
        String[] lineArray = lineOfCode.split(":");

        if(lineOfCode.endsWith(":")) {
            parsedFile = removeTheElement(parsedFile, lineIndex);
        }else{
            parsedFile[lineIndex] =  parsedFile[lineIndex].replaceAll(lineArray[0]+":", "");
        }

        String labelAddress = Integer.toHexString(lineIndex);


        this.replaceLabels(lineArray[0], labelAddress, parsedFile);
    }

    private void replaceLabels(String replaced, String value, String [] parsedFile){
        for(int i = 0; i< parsedFile.length; i++){
            parsedFile[i] = parsedFile[i].replaceAll(replaced, value);
        }
    }



    public ArrayList<Hex4digit> convertToHex(String [] parsedFile) throws Exception {
        InstructionParser instructionParser =  InstructionParser.getInstance();

        ArrayList<Hex4digit> translatedFile = new ArrayList<>();
        int lineIndex = 0;
        for (String line : parsedFile) {

            if(line.contains(":")){
                 setLabels(line.replaceAll("\\s", "")
                       , parsedFile, lineIndex);

                 line = parsedFile[lineIndex];

            }
            //NOTE: Try iterating through split(" ") string not dictionary
            //System.out.println(parsedFile[i].trim());
            StringBuilder builder = new StringBuilder();

            for (String elem : line.trim().split(" ")) {
                String instruction = instructionParser.getParser().get(elem);

                if (instruction != null){
                    builder.append(instruction);
                }else{
                    builder.append(elem);

                }
            }

            //System.out.println("Translated instruct:"+builder);



            if (builder.length() > 4 && builder.charAt(0) != '-') {
                throw new Exception("Instruction memory overflow");
            }
            //NOTE:  DEPRECATED HEX4D handles values less than 4
//            } else if (builder.length() < 4) {
//                // adds 0 until length == 4
//                while (builder.length() < 4) {
//                    builder.append("0");
//                }
//            }

            String lineOfCode = builder.toString();
            if (lineOfCode.matches("^[-0-9a-f]+$")) {
                // create hex digit
                Hex4digit hex = new Hex4digit();
                hex.setValue(lineOfCode);
                translatedFile.add(hex);
            } else {
                throw new Exception("Instruction contains unknown characters.");
            }

            lineIndex++;
        }

        this.setTranslatedCode(translatedFile);
        return this.getTranslatedCode();
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
