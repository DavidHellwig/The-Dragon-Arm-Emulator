package CS4488.Capstone.Translator;

import java.util.*;

public class InstructionParser {

    private static InstructionParser singleton = null;
    private final Map<String, String> parser
            = new HashMap<>();


    public static InstructionParser getInstance(){
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
        this.parser.put("mul", "5");
        this.parser.put("div", "6");
        this.parser.put("ldi", "7");
        this.parser.put("sti", "8");
        this.parser.put("br", "9");
        this.parser.put("brz", "a");
        this.parser.put("brn", "b");
        this.parser.put("brp", "c");
        this.parser.put("rd", "d");
        this.parser.put("wr", "e");
        this.parser.put("ldc", "f");
    }


    private void loadRegisters(){
        for(int i = 0; i < 16; i++){

            // loads quick notation into map
            String hexI = Integer.toHexString(i);
            this.parser.put("r"+hexI, hexI);

            // loads hex address notation to map
//            this.parser.put("r0x"+hexI, hexI);
        }

        // load special register address
        this.parser.put("fp", Integer.toHexString(11));
        this.parser.put("ip", Integer.toHexString(12));
        this.parser.put("sp", Integer.toHexString(13));
        this.parser.put("lr", Integer.toHexString(14));
        this.parser.put("pc", Integer.toHexString(15));

    }


    private void loadMemory(){
        for(int i = 0; i < 16; i++){

            for(int j = 0; j < 16; j++){

                String hexI = Integer.toHexString(i);
                String hexJ = Integer.toHexString(j);

                // loads short address notation to map
                this.parser.put("m"+hexI+ hexJ, hexI+ hexJ);

                // loads hex address notation to map
//                this.parser.put("m0x"+hexI+ hexJ, hexI+ hexJ);

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
