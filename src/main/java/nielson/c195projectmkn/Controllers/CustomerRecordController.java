package nielson.c195projectmkn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;

import java.io.IOException;

public class CustomerRecordController {
    @FXML
    private Pane CustomerRecordPane;
    @FXML
    private TableView customersTable;
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
    private void OnKeyTypedSearchForCustomerByIDorString(KeyEvent keyEvent) {
    }

    @FXML
    private void OnKeyTypedSearchForAppointmentByIDorString(KeyEvent keyEvent) {
    }

    @FXML
    private void OnClickDeletePart(ActionEvent actionEvent) {
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
        //AddCustomerController addCustomer = fxmlLoader.getController();
        //addCustomer.SetInventory(this.inventory);
        window.setScene(scene);
    }

    @FXML
    private void OnClickGoToEditCustomerForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EditCustomerForm.fxml"));
        Stage window = (Stage) editCustomerButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        //AddCustomerController addCustomer = fxmlLoader.getController();
        //addCustomer.SetInventory(this.inventory);
        window.setScene(scene);
    }
}
