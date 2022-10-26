package CS4488.Capstone.Translator;



import CS4488.Capstone.Library.Tools.*;
import java.util.ArrayList;
import java.util.Objects;

public class Translator  {
    // Instance variables
    private String armFile;
    private boolean loaded;
    private ArrayList<Hex4digit> translatedCode = null;
    private FileManager fileMan = FileManager.getInstance();

    private static Translator singleton = null;


    private Translator(String armFile) throws Exception {

        // Load, Read and Set Arm-file
        this.setArmFile(this.readFile(armFile));
        this.setLoaded(!(this.getArmFile().isEmpty())); // set if file is loaded successfully

       // Translate if loaded
        if(this.isLoaded()){
            // Parse Arm-file for hex conversion
            String [] parsedFile = this.parseFile(this.getArmFile());
            // convert to hex code
            this.setTranslatedCode(this.convertToHex(parsedFile));
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


    /**
     *
     */
    public void clearFile() {
        setArmFile("");
        setLoaded(false);
        setTranslatedCode(null);
    }

    /**
     *
     * @param arr
     * @param index
     * @return
     */

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


    /***
     *
     * @param line
     * @return
     */
    public String removeComments(String line){

        String lineCopy = line;

        while(lineCopy.contains("@")){

            int firstIndex = lineCopy.indexOf('@');
            if(firstIndex + 1 < lineCopy.length()){
                int endIndex = lineCopy.indexOf('@', firstIndex + 1);
                if(endIndex + 1 <= lineCopy.length()){
                    lineCopy = lineCopy.replace(lineCopy.substring(firstIndex, endIndex + 1), "");
                }

            }
        }

        return lineCopy;

    }


    private String readFile(String file) {
        String result = fileMan.readFile(file);
        return result;
    }


    /**
     *
     * @param armFile
     * @return
     */
    private String [] parseFile(String armFile){
        String noComments = removeComments(armFile)
                .replaceAll("#", "")
                .replaceAll("0x", "");

        return noComments.split(";");

    }

    /***
     *
     * @param lineOfCode
     * @param parsedFile
     * @param lineIndex
     */

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


    /***
     *
     * @param parsedFile
     * @return
     * @throws Exception
     */
    public ArrayList<Hex4digit> convertToHex(String [] parsedFile) throws Exception {
        InstructionParser instructionParser =  InstructionParser.getInstance();

        ArrayList<Hex4digit> translatedFile = new ArrayList<>();
        int lineIndex = 0;
        for (String line : parsedFile) {
            // label declaration
            if(line.contains(":")){
                // replace all label occurrence
                 setLabels(line.replaceAll("\\s", "")
                       , parsedFile, lineIndex);

                 line = parsedFile[lineIndex];

            }
            //NOTE: Try iterating through split(" ") string not dictionary
            //System.out.println(parsedFile[i].trim());
            StringBuilder builder = new StringBuilder();

            // remove trailing space
            line = line.trim();

            if (line.isEmpty()){
                continue;
            }


            // split on each instruction
            for (String elem : line.split(" ")) {
                String instruction = "";
                if(!elem.isEmpty()){
                     instruction = instructionParser.getParser().get(elem);
                }

                // adds instruction code to string if it is valid
                // else append unknown instruction
                builder.append(Objects.requireNonNullElse(instruction, elem));
            }

//            System.out.println("Line: "+line);
//
//            System.out.println("Translated instruct:"+builder);


            if (builder.length() > 4 && builder.charAt(0) != '-') {
                throw new Exception("Instruction memory overflow");
            }


            String lineOfCode = builder.toString();
            // check if line matches hex format
            if (lineOfCode.matches("^[-0-9a-f]+$")) {
                // create hex digit
                Hex4digit hex = new Hex4digit();
                hex.setValue(lineOfCode);
                translatedFile.add(hex); // adds hex code to list
            } else {
                throw new Exception("Instruction contains unknown characters.");
            }


            lineIndex++;
        }

        return translatedFile;
    }

    public static void main(String[] args) throws Exception {
        //"Translator/src/main/java/CS4488/Capstone/Translator/armcode.txt"
        //"Example Code/Program 1, Hello Branch and Math.txt"
        //"Example Code/Program 2, 4 Input 4 Operations.txt"
        //"Example Code/Program 3, Hello Memory.txt"
        //"Example Code/Program 4, Hello In Out.txt"
        //Example Code/Program 6, Dangerous Input.txt


         Translator translator = new Translator("Example Code/Program 2, 4 Input 4 Operations.txt");
         ArrayList<Hex4digit> translatedCode = translator.getTranslatedCode();

        for(Hex4digit code : translatedCode){
            System.out.println(code.getHexChars());
        }

    }
}
