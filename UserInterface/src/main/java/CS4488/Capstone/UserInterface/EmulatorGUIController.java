package CS4488.Capstone.UserInterface;


import CS4488.Capstone.Library.Tools.Hex4digit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;

import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class EmulatorGUIController {
    private final Orchestrator orc = Orchestrator.getInstance();

    private String[][] RAM;

    private File loadedProgram;




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
    private TextField IN;

    @FXML
    private TextField OUT;

    @FXML
    private TextField decToHexConversionTextField;

    @FXML
    private TextField hexToDecConversionTextField;

    @FXML
    private Button debug;

    @FXML
    private TextArea inputBox;


    @FXML
    private TextArea memoryTable;

    @FXML
    private TextFlow memoryTable2;



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

        String stringDecimal = decToHexConversionTextField.getText();


        try{

            short decimal = Short.parseShort(stringDecimal);
            decToHexConversionTextField.setText(String.copyValueOf(orc.convertToHexChars(decimal)));
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
        String stringHex = hexToDecConversionTextField.getText();
        try{
        char[] charHex = stringHex.toCharArray();
            hexToDecConversionTextField.setText(String.valueOf(orc.convertToInt(charHex)));
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


            loadedProgram = file;
            inputBox.setText(orc.loadFile(file.getAbsolutePath()));
            orc.translateAndLoad(file.getAbsolutePath());
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
        executeStep();
    }

    /**
     * Runs the program without stopping
     * @param actionEvent
     */
    @FXML
    void run(ActionEvent actionEvent){
        while(true) {
            if (orc.getError() == "Orchestrator: No Error.") {
                executeStep();

            }
            else{
                abortProgram();
            }
        }

    }

    /**
     * get the current program counter

     */
    @FXML
    void getCurrentPC(ActionEvent actionEvent){
        int temp = orc.getProgramState().pcHistory.size();

        int pc = orc.getProgramState().pcHistory.get(temp).memoryLocation;

    }

    /**
     * abort the currently running program. Can be used while program is running step by step or running in totality
     */
    @FXML
    void abortProgram(){
        orc.clearProgram();


    }

    @FXML
    void executeStep(){
        System.out.println(orc.getProgramState().printableProgramState());
        if(orc.getError() != "Orchestrator: No Error."){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(orc.getError());
            error.showAndWait();
            abortProgram();
            orc.translateAndLoad(loadedProgram.getAbsolutePath());
        }
        else{
            orc.next();

        }

        updateRAMValues();
    }




    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }





}
