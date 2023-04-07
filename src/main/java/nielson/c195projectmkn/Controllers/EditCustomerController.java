package nielson.c195projectmkn.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.*;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {
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
    @FXML
    private ComboBox<Country> customerCountryComboBox;
    @FXML
    private ComboBox<Division> customerDivisionComboBox;
    private User user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        try {
            customerCountryComboBox.getItems().setAll(ClientQuery.getAllCountries());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            customerDivisionComboBox.getItems().setAll(ClientQuery.getAllDivisions());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void OnClickUpdateCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerNameTextField.getText());
        ps.setString(2, customerAddressTextField.getText());
        ps.setString(3, customerPostalCodeTextField.getText());
        ps.setString(4, customerPhoneTextField.getText());
        ps.setInt(5, customerDivisionComboBox.getSelectionModel().getSelectedItem().getID());
        ps.setInt(6, customer.getId());
        ps.executeUpdate();
        //ObservableList<Customer> customerList = ClientQuery.getAllCustomers();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) UpdateCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        CustomerRecordController customerRecordController = fxmlLoader.getController();
        customerRecordController.setUser(this.user);
        window.setScene(scene);

    }

    @FXML
    private void OnClickReturnToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        CustomerRecordController customerRecordController = fxmlLoader.getController();
        customerRecordController.setUser(this.user);
        window.setScene(scene);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void loadCustomer(Customer selectedCustomer) throws SQLException {
        //customerIdLabel.setText(String.valueOf(selectedCustomer.getId()));
        customerNameTextField.setText(selectedCustomer.getCustomerName());
        customerAddressTextField.setText(selectedCustomer.getAddress());
        customerPostalCodeTextField.setText(selectedCustomer.getPostalCode());
        customerCountryComboBox.getItems().setAll(ClientQuery.getAllCountries());
        customerCountryComboBox.getSelectionModel().select(selectedCustomer.getDivision().getCountry());
        customerDivisionComboBox.getItems().setAll(ClientQuery.getAllDivisions());
        customerDivisionComboBox.getSelectionModel().select(selectedCustomer.getDivision());
        customerPhoneTextField.setText(selectedCustomer.getPhone());
    }
    @FXML
    private void OnActionCountryComboBox(ActionEvent actionEvent) throws SQLException {
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
        ObservableList<Division> divisionsFiltered = ClientQuery.getAllDivisions();

        int countryId = customerCountryComboBox.getSelectionModel().getSelectedItem().getId();
        customerDivisionComboBox.getItems().setAll(divisionsFiltered.stream().
                filter(x -> x.getCountry().getId() == countryId).toList());
    }

    public void setUser(User user) {
        this.user = user;
    }

}
