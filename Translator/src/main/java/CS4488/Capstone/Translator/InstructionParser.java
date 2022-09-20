package CS4488.Capstone.Translator;

import java.util.*;

public class InstructionParser {

    private static InstructionParser singleton = null;
    private final Map<String, String> parser
            = new HashMap<>();


    public static InstructionParser  getInstance(){
        if(singleton == null){
            singleton = new InstructionParser();
        }
       return singleton;
    }

    private InstructionParser(){
        loadInstructions();
        loadRegisters();
        loadMemory();
    }

    public Map<String, String> getParser() {
        return parser;
    }

    private void loadInstructions(){
        parser.put("hlt", "0");
        parser.put("ld", "1");
        parser.put("st", "2");
        parser.put("add", "3");
        parser.put("sub", "4");
        parser.put("br", "9");

    }


    private void loadRegisters(){
        for(int i = 0; i < 16; i++){
            parser.put("r"+Integer.toHexString(i), Integer.toHexString(i));
        }
    }


    private void loadMemory(){
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                parser.put("m"+Integer.toHexString(i)+ Integer.toHexString(j),
                        Integer.toHexString(i)+ Integer.toHexString(j));
            }
        }
    }

    public static void main(String[] args) {
        InstructionParser instructionParser= new InstructionParser();

        for (Map.Entry<String, String> me :
                instructionParser.parser.entrySet()) {

            // Printing keys
            System.out.print(me.getKey() + ":");
            System.out.println(me.getValue());
        }

    }


}
