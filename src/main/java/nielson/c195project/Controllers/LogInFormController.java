package nielson.c195project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LogInFormController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}