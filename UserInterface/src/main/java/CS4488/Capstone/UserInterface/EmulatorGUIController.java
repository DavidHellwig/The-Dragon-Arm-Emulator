package CS4488.Capstone.UserInterface;


import CS4488.Capstone.Library.Tools.Hex4digit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;

import javafx.stage.FileChooser;


import java.io.File;
import java.util.ArrayList;

public class EmulatorGUIController {
    private final Orchestrator orc = Orchestrator.getInstance();

    private String[][] RAM;


    @FXML
    private Button exitButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button assembleButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button runButton;

    @FXML
    private Button abortButton;

    @FXML
    private Button hexConverterDecimalToHexButton;

    @FXML
    private TextField pc;

    @FXML
    private TextField AC;

    @FXML
    private TextField IN;

    @FXML
    private TextField OUT;

    @FXML
    private TextField conversionTextField;

    @FXML
    private Button debug;

    @FXML
    private TextArea inputBox;

    @FXML
    private TextArea outputBox;

    @FXML
    private TextArea memoryTable;
    

    @FXML
    private Button hexConverterHexToDecimalButton;

    public EmulatorGUIController() {


    }


    /**
     * Convert a decimal to hex
     * @param actionEvent
     */
    @FXML
    void decimalToHex(ActionEvent actionEvent){

        String stringDecimal = conversionTextField.getText();


        try{

            short decimal = Short.parseShort(stringDecimal);
            conversionTextField.setText(String.copyValueOf(orc.convertToHexChars(decimal)));
        }
        catch(Exception ignored){
        }







    }

    /**
     * Convert hex to decimal
     * @param actionEvent
     */
    @FXML
    void hexToDecimal(ActionEvent actionEvent){
        String stringHex = conversionTextField.getText();
        try{
        char[] charHex = stringHex.toCharArray();
            conversionTextField.setText(String.valueOf(orc.convertToInt(charHex)));
        }
        catch (Exception ignored){
        }





    }

    /**
     * Load a file into the input box and load the program from the file
     * @param actionEvent
     */
    @FXML
    void loadFile(ActionEvent actionEvent){

        FileChooser txtChooser = new FileChooser();
        txtChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Txt files","*.txt"));
        File file = txtChooser.showOpenDialog(null);
        if (file != null){



            inputBox.setText(orc.loadFile(file.getAbsolutePath()));
        }





    }
    @FXML
    void initializeMemoryTable(ActionEvent actionEvent){
        short annoying = 0;
        RAM = new String[256][17];
        for (int i = 1;i<256;i++){
            for(int j = 1; j < 17; j++){
                RAM[i][j] = String.valueOf(orc.convertToHexChars(annoying));
            }
        }
        RAM[0][0] = "Loc     ";
        for(int i = 1;i<17;i++){
            short painful = (short)(i-1);
            RAM[0][i] = String.valueOf(orc.convertToHexChars(painful));
        }
        for(int i = 1;i<256;i++){
            short location = (short)(i-1);
            RAM[i][0] = String.valueOf(orc.convertToHexChars(location));
        }
        printInitialRAMValues();

    }

    @FXML
    void printInitialRAMValues(){

        String memArray = "";
        for (int i = 0; i< 256;i++){
            for( int j = 0; j < 16; j++){
                memArray += RAM[i][j] + "  ";
            }
            memArray += "\n";
        }
        memoryTable.setText(memArray);
    }
    //Update RAM values, incomplete
    @FXML
    void updateRAMValues(){
        //String newMemArray = orc.getProgramState();
        ArrayList<ArrayList<Hex4digit>> newHex4DigitMemarray = orc.getProgramState().memoryStateHistory;
        int x = 0;
    }

    /**
     * Execute step once
     * @param actionEvent
     */
    @FXML
    void next(ActionEvent actionEvent){
        updateRAMValues();
    }

    /**
     * Runs the program without stopping
     * @param actionEvent
     */
    @FXML
    void run(ActionEvent actionEvent){

    }

    /**
     * get the current program counter
     * @param actionEvent
     */
    @FXML
    void getCurrentPC(ActionEvent actionEvent){

    }

    /**
     * Update the ac
     */
    @FXML
    void updateAC(){

    }

    /**
     * abort the currently running program. Can be used while program is running step by step or running in totality
     */
    @FXML
    void abortProgram(){
        //ToDo use this to insert a halt statement into the program state so orc stops


    }

    /**
     * Steps through the given code by 1 line
     */
    @FXML
    void step(){

    }








    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }



}
