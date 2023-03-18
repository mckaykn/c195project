package nielson.c195projectmkn.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {

    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passWordTextField;
    @FXML
    private Label locationDisplayLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String countryLabel = ZoneId.systemDefault().toString();
        locationDisplayLabel.setText(countryLabel);
    }

}
