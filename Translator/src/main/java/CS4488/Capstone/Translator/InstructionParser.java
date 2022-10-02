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
        return this.parser;
    }

    private void loadInstructions(){
        this.parser.put("hlt", "0");
        this.parser.put("ld", "1");
        this.parser.put("st", "2");
        this.parser.put("add", "3");
        this.parser.put("sub", "4");
        this.parser.put("br", "9");

    }


    private void loadRegisters(){
        for(int i = 0; i < 16; i++){

            // loads quick notation into parser
            this.parser.put("r"+Integer.toHexString(i),
                    Integer.toHexString(i));

            // loads hex address notation to parser
            this.parser.put("r0x"+Integer.toHexString(i),
                    Integer.toHexString(i));
        }

        // load special register names
        this.parser.put("fp", Integer.toHexString(11));
        this.parser.put("ip", Integer.toHexString(12));
        this.parser.put("sp", Integer.toHexString(13));
        this.parser.put("lr", Integer.toHexString(14));
        this.parser.put("pc", Integer.toHexString(15));

    }


    private void loadMemory(){
        for(int i = 0; i < 16; i++){

            for(int j = 0; j < 16; j++){
                this.parser.put("m"+Integer.toHexString(i)+ Integer.toHexString(j),
                        Integer.toHexString(i)+ Integer.toHexString(j));


                // loads hex address notation to parser
                this.parser.put("m0x"+Integer.toHexString(i)+ Integer.toHexString(j),
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
