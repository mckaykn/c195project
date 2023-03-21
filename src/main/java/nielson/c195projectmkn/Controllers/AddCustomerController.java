package nielson.c195projectmkn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;

import java.io.IOException;

public class AddCustomerController {
    @FXML
    private TextField customerIDTextField;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerAddressTextField;
    @FXML
    private TextField customerPostalCodeTextField;
    @FXML
    private TextField customerPhoneTextField;
    @FXML
    private Label customerCreateDateLabel;
    @FXML
    private Label customerCreatedByLabel;
    @FXML
    private Label customerLastUpdatedLabel;
    @FXML
    private ComboBox customerDivisionComboBox;
    @FXML
    private Button CreateCustomerButton;
    @FXML
    private Button backButton;

    @FXML
    private void OnClickSaveCustomer(ActionEvent actionEvent) {
    }

    @FXML
    private void OnClickReturnToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        //MainController main = fxmlLoader.getController();
        //main.SetInventory(inventory);
        window.setScene(scene);
    }
}
