package nielson.c195projectmkn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Objects;
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
    @FXML
    private Button logInButton;
    @FXML
    private Label LogInErrorDisplayLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String countryLabel = ZoneId.systemDefault().toString();
        locationDisplayLabel.setText(countryLabel);
    }

    @FXML
    private void OnClickLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        String userName = userNameTextField.getText();
        String password = passWordTextField.getText();
        User user = ClientQuery.getUser(userName);
        if (user != null){
            if (Objects.equals(password, user.getPassword())){
                System.out.println("Log In Successful!");
                OpenCustomerRecord();
            }
            else {
                LogInErrorDisplayLabel.setText("Username or Password was not recognized!");
                System.out.println("Log in Not Successful!");
            }
        }
        else {
            LogInErrorDisplayLabel.setText("Username or Password was not recognized!");
            System.out.println("Log in Not Successful!");
        }
    }
    @FXML
    private void OpenCustomerRecord() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Customer Record");
        stage.setScene(scene);
        stage.show();
    }
}
