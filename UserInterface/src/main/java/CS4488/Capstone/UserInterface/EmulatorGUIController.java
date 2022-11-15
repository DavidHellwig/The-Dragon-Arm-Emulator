package CS4488.Capstone.UserInterface;


import CS4488.Capstone.Library.Tools.Hex4digit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;

import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;


import java.io.File;
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
    private TextField R0;

    @FXML
    private TextField R1;

    @FXML
    private TextField R2;

    @FXML
    private TextField R3;

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
    //Decimal Conversion Methods And Buttons

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

    //File Management Methods and Buttons

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

        initializeMemoryTable();

    }

    //Memory table Methods and Buttons

    /**
     * initialize the memory table, need to refactor

     */
    @FXML
    void initializeMemoryTable(){
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

    /**
     * Print the intial ram values
     */
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
        int x = 0; // A relic


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
     * Runs the program without stopping, unless the program has ended or an error is reached
     * @param actionEvent
     */
    @FXML
    void run(ActionEvent actionEvent) throws InterruptedException {

        while(true) {
            if (orc.getProgramState().registers[15].getValue() == -1){
                Alert end = new Alert(Alert.AlertType.WARNING);
                end.setContentText("End of file reached");
                end.showAndWait();

                break;
            }

            if (orc.getError() == "Orchestrator: No Error.") {
                executeStep();

            }
            else if (orc.getError() != "Orchestrator: No Error."){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(orc.getError());
                alert.showAndWait();
                break;
            }


        }



    }

    /**
     * Sets the value of the registers
     */
    @FXML
    void getRegisters(){
        R0.setText(String.valueOf(orc.getProgramState().registers[0].getValue()));

        R1.setText(String.valueOf(orc.getProgramState().registers[1].getValue()));

        R2.setText(String.valueOf(orc.getProgramState().registers[2].getValue()));

        R3.setText(String.valueOf(orc.getProgramState().registers[3].getValue()));

        pc.setText(String.valueOf(orc.getProgramState().registers[15].getValue()));

    }

    /**
     * Writes input from orchestrator to output box
     */
    @FXML
    void writeToOutput(){
        //TODO figure this out

    }



    /**
     * abort the currently running program. Can be used while program is running step by step or running in totality
     */
    @FXML
    void abortProgram(){
        orc.clearProgram();


    }

    /**
     * executes one step through the code, unless the end of the program is reached or an error is reached
     */
    @FXML
    void executeStep(){

        if(orc.getError() != "Orchestrator: No Error."){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(orc.getError());
            error.showAndWait();
            abortProgram();
            orc.translateAndLoad(loadedProgram.getAbsolutePath());
        }
        else if (orc.getProgramState().registers[15].getValue() != -1){
            getRegisters();
            orc.next();

        }
        else if (orc.getProgramState().registers[15].getValue() == -1){
            Alert end = new Alert(Alert.AlertType.WARNING);
            end.setContentText("End of file reached");
            end.showAndWait();



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
