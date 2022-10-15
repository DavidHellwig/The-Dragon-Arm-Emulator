package CS4488.Capstone.UserInterface;


import CS4488.Capstone.Library.Tools.ProgramState;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Callback;


import java.io.File;
import java.util.ArrayList;

public class EmulatorGUIController {
    private final Orchestrator orc = new Orchestrator();

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
            conversionTextField.setText(String.valueOf(orc.convertToShort(charHex)));
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

        initializeMemoryTable();



    }
    @FXML
    void initializeMemoryTable(){
        short annoying = 0;
        RAM = new String[256][16];
        for (int i = 1;i<255;i++){
            for(int j = 1; j< 16; j++){
                RAM[i][j] = String.valueOf(orc.convertToHexChars(annoying));
            }
        }
        RAM[0][0] = "Loc";
        for(int i = 1;i<16;i++){
            short painful = (short)(i-1);
            RAM[0][i] = String.valueOf(orc.convertToHexChars(painful));
        }
        for(int i = 1;i<256;i++){
            short location = (short)(i-1);
            RAM[i][0] = String.valueOf(orc.convertToHexChars(location));
        }


        //memoryTable.setText(RAM[0][0]);
        
    }






    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }



}
