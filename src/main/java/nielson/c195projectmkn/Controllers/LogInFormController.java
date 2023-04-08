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
import nielson.c195projectmkn.helper.UserActivityLogger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {
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
    public User user = null;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    Locale currentLanguage = Locale.getDefault();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentLanguage == Locale.FRENCH || currentLanguage == Locale.FRANCE || currentLanguage == Locale.CANADA_FRENCH) {
            userNameLabel.setText("Nom d'utilisateur:");
            passwordLabel.setText("Mot de passe");
            logInButton.setText("Connexion");
        }
        String countryLabel = ZoneId.systemDefault().toString();
        locationDisplayLabel.setText(countryLabel);
    }

    @FXML
    private void OnClickLogIn(ActionEvent actionEvent) throws SQLException, IOException {
        String userName = userNameTextField.getText();
        String password = passWordTextField.getText();


        user = ClientQuery.getUser(userName);
        if (user != null){
            if (Objects.equals(password, user.getPassword())){
                UserActivityLogger.logActivity(userName, true);
                if (Locale.getDefault() == Locale.ENGLISH) {
                    System.out.println("Log In Successful!");
                }
                if (Locale.getDefault() == Locale.FRENCH) {
                    System.out.println("Connexion réussie");
                }
                OpenCustomerRecord();
            }
            else {
                if (Locale.getDefault() == Locale.ENGLISH) {
                    UserActivityLogger.logActivity(userName, false);
                    LogInErrorDisplayLabel.setText("Username or Password was not recognized!");
                    System.out.println("Log in Not Successful!");
                }
                if (currentLanguage == Locale.FRENCH || currentLanguage == Locale.FRANCE || currentLanguage == Locale.CANADA_FRENCH) {
                    LogInErrorDisplayLabel.setText("Le nom d'utilisateur ou le mot de passe n'a pas été reconnu");
                    System.out.println("Connexion échouée");
                }
            }
        }
        else {
            if (Locale.getDefault() == Locale.ENGLISH) {
                LogInErrorDisplayLabel.setText("Username or Password was not recognized!");
                System.out.println("Log in Not Successful!");
            }
            if (currentLanguage == Locale.FRENCH || currentLanguage == Locale.FRANCE || currentLanguage == Locale.CANADA_FRENCH) {
                LogInErrorDisplayLabel.setText("Le nom d'utilisateur ou le mot de passe n'a pas été reconnu");
                System.out.println("Connexion échouée");
            }
        }
    }
    @FXML
    private void OpenCustomerRecord() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) logInButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        CustomerRecordController customerRecord = fxmlLoader.getController();
        customerRecord.setUser(this.user);
        customerRecord.checkForAppointmentsWithin15Minutes();
        window.setScene(scene);
    }
}
