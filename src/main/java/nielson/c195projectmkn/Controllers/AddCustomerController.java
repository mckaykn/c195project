package nielson.c195projectmkn.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.Country;
import nielson.c195projectmkn.Models.Customer;
import nielson.c195projectmkn.Models.Division;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
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
    private ComboBox<Division> customerDivisionComboBox;
    @FXML
    private Button CreateCustomerButton;
    @FXML
    private Button backButton;
    private Customer customer;
    private LocalDateTime currentDateTime;
    private LocalDateTime lastUpdated;
    private User user;
    private Customer newCustomer;
    @FXML
    private ComboBox<Country> customerCountryComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerDivisionComboBox.getItems().addAll(ClientQuery.getAllDivisions());
            customerCountryComboBox.getItems().addAll(ClientQuery.getAllCountries());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Set the cell factory to customize the rendering of each item
        customerCountryComboBox.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>() {
            @Override
            public ListCell<Country> call(ListView<Country> param) {
                return new ListCell<Country>() {
                    @Override
                    protected void updateItem(Country item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName()); // Replace "getProperty()" with the actual property getter method name
                        }
                    }
                };
            }
        });
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @FXML
    private void OnClickSaveCustomer(ActionEvent actionEvent) throws IOException, SQLException {
        currentDateTime = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();

        newCustomer = new Customer(0, customerNameTextField.getText(), customerAddressTextField.getText(),
                customerPostalCodeTextField.getText(), customerPhoneTextField.getText(), currentDateTime,
                user.getName(), lastUpdated, user.getName(), customerDivisionComboBox.getSelectionModel().getSelectedItem());
        ClientQuery.SaveCustomer(newCustomer);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) CreateCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        window.setScene(scene);
        //CustomerRecordController customerRecordController = fxmlLoader.getController();
        //customerRecordController.SetInventory(inventory);



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

    @FXML
    private void OnActionCountryComboBox(ActionEvent actionEvent) {
        customerDivisionComboBox.setCellFactory(new Callback<ListView<Division>, ListCell<Division>>() {
            @Override
            public ListCell<Division> call(ListView<Division> param) {
                return new ListCell<Division>() {
                    @Override
                    protected void updateItem(Division item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName()); // Replace "getProperty()" with the actual property getter method name
                        }
                    }
                };
            }
        });
        //customerDivisionComboBox.getItems().
    }

    public void setUser(User user) {
        this.user = user;
    }
}