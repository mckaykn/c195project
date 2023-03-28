package nielson.c195projectmkn.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.Appointment;
import nielson.c195projectmkn.Models.Customer;
import nielson.c195projectmkn.Models.Division;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.GuiUtils;
import nielson.c195projectmkn.helper.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerRecordController implements Initializable {
    @FXML
    private Pane CustomerRecordPane;
    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn customerIdColumn;
    @FXML
    private TableColumn customerNameColumn;
    @FXML
    private TableColumn customerAddressColumn;
    @FXML
    private TableColumn customerPostalCodeColumn;
    @FXML
    private TableColumn customerPhoneColumn;
    @FXML
    private TableColumn customerCreateDateColumn;
    @FXML
    private TableColumn customerCreatedByColumn;
    @FXML
    private TableColumn customerLastUpdatedColumn;
    @FXML
    private TableColumn customerDivisionColumn;
    @FXML
    private TextField customerTableSearchBar;
    @FXML
    private TextField appointmentTableSearchBar;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button editCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button editAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableView appointmentsTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn startColumn;
    @FXML
    private TableColumn endColumn;
    @FXML
    private TableColumn createDateColumn;
    @FXML
    private TableColumn createdByColumn;
    @FXML
    private TableColumn lastUpdateColumn;
    @FXML
    private TableColumn appointmentsCustomerIdColumn;
    @FXML
    private TableColumn appointmentsUserIdColumn;
    @FXML
    private TableColumn appointmentsContactIdColumn;
    @FXML
    private Label noCustomerFoundLabel;
    @FXML
    private Label noAppointmentFoundLabel;
    @FXML
    private TableColumn customerLastUpdatedByColumn;
    private ObservableList<Customer> customers;
    private ObservableList<Appointment> appointments;
    private Customer customer;
    private User user;


    @FXML
    private void OnKeyTypedSearchForCustomerByIDorString(KeyEvent keyEvent) {
    }

    @FXML
    private void OnKeyTypedSearchForAppointmentByIDorString(KeyEvent keyEvent) {
    }

    @FXML
    private int OnClickDeleteCustomerBySelection(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        customers.remove(selectedCustomer);

        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedCustomer.getId());
        return ps.executeUpdate();
    }

    @FXML
    private void OnClickOpenAddAppointment(ActionEvent actionEvent) {
    }

    @FXML
    private void OnClickOpenEditAppointment(ActionEvent actionEvent) {
    }

    @FXML
    private void OnClickDeleteAppointment(ActionEvent actionEvent) {
    }

    @FXML
    private void OnClickExitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    private void onClickGoToAddCustomerForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddCustomerForm.fxml"));
        Stage window = (Stage) addCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        AddCustomerController addCustomer = fxmlLoader.getController();
        addCustomer.setUser(user);
        window.setScene(scene);
    }

    @FXML
    private void OnClickGoToEditCustomerForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditCustomerForm.fxml"));
        Stage window = (Stage) editCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        AddCustomerController addCustomer = fxmlLoader.getController();
        addCustomer.setCustomer(this.customer);
        //AddCustomerController addCustomer = fxmlLoader.getController();
        //addCustomer.SetInventory(this.inventory);
        window.setScene(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customers = ClientQuery.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerCreateDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        customerCreatedByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        customerLastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        customerLastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        customerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
        customersTable.setItems(customers);
        GuiUtils.autoResizeColumns(customersTable);
        try {
            appointments = ClientQuery.getAllAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        appointmentsCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        appointmentsUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        appointmentsContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        appointmentsTable.setItems(appointments);
        GuiUtils.autoResizeColumns(appointmentsTable);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
