package nielson.c195projectmkn.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.Appointment;
import nielson.c195projectmkn.Models.Customer;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.GuiUtils;
import nielson.c195projectmkn.helper.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;

/**
 * @author mckaykn
 * this controller acts as the primary platform to see customers, and their associated appointments.
 */
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
    private TableColumn<Timestamp, Customer> customerCreateDateColumn;
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

    private ObservableList<Appointment> appointments15Minutes;
    private Customer customer;
    private User user;
    @FXML
    private RadioButton MonthRadioButton;
    @FXML
    private RadioButton WeekRadioButton;
    @FXML
    private RadioButton allRadioButton;
    @FXML
    private Button goToReportsButton;
    @FXML
    private Button logOutButton;

    @FXML
    private int OnClickDeleteCustomerBySelection(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Must select customer!");
            alert.setHeaderText("No customer selected, cannot delete customer!");
            alert.setContentText("Please select customer to be deleted.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer?!");
            alert.setHeaderText("Are you sure you want to remove this customer?");
            alert.setContentText("Removing this customer will also remove all associated Appointments!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                String Asql = "DELETE FROM appointments WHERE Customer_ID = ?";
                PreparedStatement ps = JDBC.connection.prepareStatement(Asql);
                ps.setInt(1, selectedCustomer.getId());
                ps.executeUpdate();
                String Csql = "DELETE FROM customers WHERE Customer_ID = ?";
                PreparedStatement psC = JDBC.connection.prepareStatement(Csql);
                psC.setInt(1, selectedCustomer.getId());
                psC.executeUpdate();
                customers.remove(selectedCustomer);
                appointments = ClientQuery.getAllAppointments();
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
                Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                confirmation.setTitle("Customer Record has been deleted!");
                confirmation.setHeaderText("Customer has successfully been deleted!");
                confirmation.showAndWait();
            }
        }
        return 0;
    }

    @FXML
    private void OnClickOpenAddAppointment(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAppointmentForm.fxml"));
        Stage window = (Stage) addAppointmentButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        AddAppointmentFormController addAppointment = fxmlLoader.getController();
        addAppointment.setUser(this.user);
        window.setScene(scene);
    }

    @FXML
    private int OnClickDeleteAppointment(ActionEvent actionEvent) throws SQLException {
        Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        appointments.remove(selectedAppointment);
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedAppointment.getID());
        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("An appointment has been cancelled!");
        confirmation.setHeaderText("ID: " + selectedAppointment.getID() + " ,Type: " + selectedAppointment.getType() + " has been cancelled.");
        confirmation.show();
        return ps.executeUpdate();

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
        addCustomer.setUser(this.user);
        window.setScene(scene);
    }

    @FXML
    private void OnClickGoToEditCustomerForm(ActionEvent actionEvent) throws IOException, SQLException {
        Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Must select customer!");
            alert.setHeaderText("No customer selected, cannot edit part!");
            alert.setContentText("Please select customer to be edited.");
            alert.showAndWait();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditCustomerForm.fxml"));
            Stage window = (Stage) editCustomerButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            EditCustomerController editCustomer = fxmlLoader.getController();
            editCustomer.setCustomer(selectedCustomer);
            editCustomer.loadCustomer(selectedCustomer);
            editCustomer.setUser(this.user);
            window.setScene(scene);
        }
    }
    @FXML
    private void OnClickOpenEditAppointment(ActionEvent actionEvent)throws IOException, SQLException {
        Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Must select appointment!");
            alert.setHeaderText("No appointment selected, cannot edit!");
            alert.setContentText("Please select appointment to be edited.");
            alert.showAndWait();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditAppointmentForm.fxml"));
            Stage window = (Stage) editAppointmentButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 500, 600);
            EditAppointmentFormController editAppointment = fxmlLoader.getController();
            editAppointment.setAppointment(selectedAppointment);
            editAppointment.loadAppointment(selectedAppointment);
            editAppointment.setUser(this.user);
            window.setScene(scene);
        }
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

    @FXML
    private void OnClickOrganizeAppointmentsByWeek(ActionEvent actionEvent) throws SQLException {
        appointments = ClientQuery.getAllAppointmentsByWeek();
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

    @FXML
    private void OnClickOrganizeAppointmentsByMonth(ActionEvent actionEvent) throws SQLException {
        appointments = ClientQuery.getAllAppointmentsByMonth();
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

    @FXML
    private void OnClickShowAllAppointments(ActionEvent actionEvent) throws SQLException {
        appointments = ClientQuery.getAllAppointments();
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

    public void checkForAppointmentsWithin15Minutes() throws SQLException {
        appointments15Minutes = ClientQuery.getAppointmentsWithin15Minutes();
        if (appointments15Minutes.size() > 0) {
            Alert appointmentSoon = new Alert(Alert.AlertType.INFORMATION);
            appointmentSoon.setTitle("There is an upcoming appointment!");
            appointmentSoon.setHeaderText("Appointment " + appointments15Minutes.get(0).getID() +
                    " at " +ClientQuery.timestampToUTC(appointments15Minutes.get(0).getStart())+  " will occur within 15 Minutes!");
            appointmentSoon.showAndWait();
        }
        else {
            Alert noAppointmentSoon = new Alert(Alert.AlertType.INFORMATION);
            noAppointmentSoon.setTitle("No upcoming appointments");
            noAppointmentSoon.setHeaderText("There are no upcoming appointment within 15 minutes.");
            noAppointmentSoon.show();
        }
    }

    @FXML
    private void OnClickOpenReportsForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ReportsForm.fxml"));
        Stage window = (Stage) goToReportsButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        ReportsFormController reportsForm = fxmlLoader.getController();
        reportsForm.setUser(this.user);
        window.setScene(scene);
    }

    @FXML
    private void OnClickLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInForm.fxml"));
        Stage window = (Stage) goToReportsButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        window.setScene(scene);

    }
}
