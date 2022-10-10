package CS4488.Capstone.UserInterface;

import CS4488.Capstone.Library.Tools.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;

public class EmulatorGUIController {
    private Orchestrator orc = new Orchestrator();




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
    private TextField debug;

    @FXML
    private TextArea inputBox;

    @FXML
    private TextArea outputBox;

    @FXML
    private TableView memoryTable;

    @FXML
    private TableColumn memoryLocationColumn;



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

        short decimal = Short.valueOf(stringDecimal);


        //TODO fix this because it will not work if you hit the button more than once
        conversionTextField.setText(String.copyValueOf(orc.convertToHexChars(decimal)));



    }

    /**
     * Convert hex to decimal
     * @param actionEvent
     */
    @FXML
    void hexToDecimal(ActionEvent actionEvent){
        String stringHex = conversionTextField.getText();

        char[] charHex = stringHex.toCharArray();
        //TODO fix this because it will not work if you hit the button more than once
        conversionTextField.setText(String.valueOf(orc.convertToShort(charHex)));



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
            //Removing this until potential conflicts sorted out
            //inputBox.setText(orc.fileManager.fileToString(file.getAbsolutePath()));
        }



    }

    /**
     * This will load default memory values into the memory table. This should never be allowed to be used directly
     * by the user
     */
    void initializeMemoryTable(){
        ObservableList<char[]> locColumnValues = FXCollections.observableArrayList();
        for(int i = 0; i<256;i++){
            locColumnValues.add(orc.convertToHexChars((short)i));
        }
        int x = 0;
        //memoryLocationColumn.getColumns().addAll(locColumnValues);


    }


    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }



}
