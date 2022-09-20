package CS4488.Capstone.UserInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import CS4488.Capstone.System.Orchestrator;
import javafx.scene.control.TextField;

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
    private Button hexConverterHexToDecimalButton;

    @FXML
    void decimalToHex(ActionEvent actionEvent){
        String stringDecimal = conversionTextField.getText();

        short decimal = Short.valueOf(stringDecimal);


        //TODO fix this because it will not work if you hit the button more than once
        conversionTextField.setText(String.copyValueOf(orc.convertToHexChars(decimal)));


        //Why is this copyValueOf?
        System.out.println(String.copyValueOf(orc.convertToHexChars(decimal)));

    }

    @FXML
    void hexToDecimal(ActionEvent actionEvent){
        String stringHex = conversionTextField.getText();

        char[] charHex = stringHex.toCharArray();
        //TODO fix this because it will not work if you hit the button more than once
        conversionTextField.setText(String.valueOf(orc.convertToShort(charHex)));


    }



    @FXML
    void exit(){
        System.exit(0);
    }



}
