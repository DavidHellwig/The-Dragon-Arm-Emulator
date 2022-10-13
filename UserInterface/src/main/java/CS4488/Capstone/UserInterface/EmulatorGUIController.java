package CS4488.Capstone.UserInterface;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import CS4488.Capstone.System.Orchestrator;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;

public class EmulatorGUIController {
    private Orchestrator orc = new Orchestrator();


    private ObservableList<Integer> locColumnValues = FXCollections.observableArrayList();

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
    private TableView<Integer> memoryTable;

    @FXML
    private TableColumn<Integer,String> memoryLocationColumn;
    //This is annoying to look at
    @FXML
    private TableColumn<Integer,Integer> col0;

    @FXML
    private TableColumn<Integer,Integer> col1;

    private TableColumn<Integer,Integer> col2;

    private TableColumn<Integer,Integer> col3;

    private TableColumn<Integer,Integer> col4;

    private TableColumn<Integer,Integer> col5;

    private TableColumn<Integer,Integer> col6;

    private TableColumn<Integer,Integer> col7;

    private TableColumn<Integer,Integer> col8;

    private TableColumn<Integer,Integer> col9;

    private TableColumn<Integer,Integer> colA;

    private TableColumn<Integer,Integer> colB;

    private TableColumn<Integer,Integer> colC;

    private TableColumn<Integer,Integer> colD;

    private TableColumn<Integer,Integer> colE;

    private TableColumn<Integer,Integer> colF;







    @FXML
    private Button hexConverterHexToDecimalButton;

    public EmulatorGUIController() {
        initializeMemoryTable();
    }


    /**
     * Convert a decimal to hex
     * @param actionEvent
     */
    @FXML
    void decimalToHex(ActionEvent actionEvent){

        String stringDecimal = conversionTextField.getText();


        try{

            short decimal = Short.valueOf(stringDecimal);
            conversionTextField.setText(String.copyValueOf(orc.convertToHexChars(decimal)));
        }
        catch(Exception exception){
            ;
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
        catch (Exception exception){
            ;
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
            //Removing this until potential conflicts sorted out
            //inputBox.setText(orc.fileManager.fileToString(file.getAbsolutePath()));
        }



    }

    /**
     * This will load default memory values into the memory table. This should never be allowed to be used directly
     * by the user
     */
    void initializeMemoryTable(){

        //TODO fix memory table as it sucks a**
    }


    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }



}
