package CS4488.Capstone.UserInterface;

import CS4488.Capstone.Library.Tools.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import CS4488.Capstone.System.Orchestrator;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class EmulatorGUIController {
    private Orchestrator orc = new Orchestrator();

    private FileManager fm = new FileManager();

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
    private TextField inputBox;

    @FXML
    private TextField outputBox;

    @FXML
    private TextField memoryBox;



    @FXML
    private Button hexConverterHexToDecimalButton;

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


        //Why is this copyValueOf?
        System.out.println(String.copyValueOf(orc.convertToHexChars(decimal)));

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
     * Load a file into the input box
     * @param actionEvent
     */
    @FXML
    void loadFile(ActionEvent actionEvent){
        FileChooser txtChooser = new FileChooser();
        txtChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Txt files","*.txt"));
        File file = txtChooser.showOpenDialog(null);
        if (file != null){
            inputBox.setText(fm.fileToString(file.getAbsolutePath()));
        }
    }


    /**
     * Exit the program
     */
    @FXML
    void exit(){
        System.exit(0);
    }



}
