package nielson.c195projectmkn.Controllers;

import javafx.collections.ObservableList;
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
import nielson.c195projectmkn.Models.Customer;
import nielson.c195projectmkn.Models.Division;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.JDBC;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EditCustomerController {
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerAddressTextField;
    @FXML
    private TextField customerPostalCodeTextField;
    @FXML
    private TextField customerPhoneTextField;
    @FXML
    private Button UpdateCustomerButton;
    @FXML
    private Button backButton;
    private Customer customer;
    private Customer updatedCustomer;
    @FXML
    private ComboBox customerCountryComboBox;
    @FXML
    private ComboBox<Division> customerDivisionComboBox;
    private User user;
    @FXML
    private Label customerIdLabel;


    @FXML
    private void OnClickUpdateCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerNameTextField.getText());
        ps.setString(2, customerAddressTextField.getText());
        ps.setString(3, customerPostalCodeTextField.getText());
        ps.setString(4, customerPhoneTextField.getText());
        ps.setInt(5, customerDivisionComboBox.getSelectionModel().getSelectedItem().getID());
        ps.setInt(6, Integer.parseInt(customerIdLabel.getText()));
        ps.executeUpdate();

        //ObservableList<Customer> customerList = ClientQuery.getAllCustomers();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) UpdateCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        window.setScene(scene);

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void loadCustomer(Customer selectedCustomer) {
        customerIdLabel.setText(String.valueOf(selectedCustomer.getId()));
        customerNameTextField.setText(selectedCustomer.getCustomerName());
        customerAddressTextField.setText(selectedCustomer.getAddress());
        customerPostalCodeTextField.setText(selectedCustomer.getPostalCode());
        customerDivisionComboBox.getSelectionModel().select(selectedCustomer.getDivision());
        customerPhoneTextField.setText(selectedCustomer.getPhone());
    }

    public void setUser(User user) {
        this.user = user;
    }
}
