package CS4488.Capstone.UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmulatorGUIController {
    @FXML
    private Button exitButton;

    @FXML
    private Button loadButton;

    @FXML
    void exit(){
        System.exit(0);
    }


}
