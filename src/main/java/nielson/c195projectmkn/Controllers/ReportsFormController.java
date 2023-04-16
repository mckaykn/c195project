package nielson.c195projectmkn.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.Appointment;
import nielson.c195projectmkn.Models.Contact;
import nielson.c195projectmkn.Models.Month;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.GuiUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * This class includes all the reports needed in the program. Including the 3f criteria of adding another report.
 */

public class ReportsFormController implements Initializable {
    @FXML
    private Label totalNumberLabel;
    @FXML
    private ComboBox<Month> reportsMonthComboBox;
    @FXML
    private ComboBox<String> reportsTypeComboBox;

    Month[] months = {
            new Month(1, "January"),
            new Month(2, "February"),
            new Month(3, "March"),
            new Month(4, "April"),
            new Month(5, "May"),
            new Month(6, "June"),
            new Month(7, "July"),
            new Month(8, "August"),
            new Month(9, "September"),
            new Month(10, "October"),
            new Month(11, "November"),
            new Month(12, "December")
    };
    @FXML
    private Button totalAppointmentsButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<Contact> contactsComboBox;
    @FXML
    private TableView contactTableView;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn startColumn;
    @FXML
    private TableColumn endColumn;
    @FXML
    private TableColumn customerIDColumn;
    @FXML
    private PieChart contactPieChart;
    private User user;

    public static boolean isTimestampInMonth(Timestamp timestamp, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        boolean result = cal.get(Calendar.MONTH) == month - 1;
        return result;
    }

    /**
     * This functions runs on load and populates the form with all three reports. The contactPieChart is filled using lambda to iterate
     * over my list of contacts and my list of appointments to assign size to each pie chart slice. This lambda saves me from manually
     * iterating over each list.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsMonthComboBox.getItems().setAll(months);
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        try {
            appointments = ClientQuery.getAllAppointments();
            contactsComboBox.getItems().setAll(ClientQuery.getAllContacts());
            reportsTypeComboBox.getItems().setAll(ClientQuery.getAllAppointments().stream().map(Appointment::getType).toArray(String[]::new));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Contact> contacts = contactsComboBox.getItems();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Contact contact : contacts) {
            pieChartData.add(new PieChart.Data(contact.getName(), appointments.stream()
                    .filter(appointment -> appointment.getContactID() == contact.getContactID())
                    .toList().size()));
        }

        contactPieChart.setData(pieChartData);
    }

    /**
     * Functions as a filter to stream both Month ComboBox and Type ComboBox together to find appointments with the same appointmentID. Lambda is
     * used to make this process much faster and simpler.
     * @throws SQLException
     */
    private void calculateTotalReports() throws SQLException {
        if (reportsMonthComboBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (reportsTypeComboBox.getSelectionModel().getSelectedItem() == null){
            return;
        }
        ObservableList<Appointment> appointments = ClientQuery.getAllAppointments();
        List<Appointment> appointmentList = appointments
                                                                .stream()
                                                                .filter(appointment -> isTimestampInMonth(appointment.getStart(), reportsMonthComboBox.getSelectionModel().getSelectedItem().getId()) && Objects.equals(appointment.getType(), reportsTypeComboBox.getSelectionModel().getSelectedItem()))
                                                                .toList();
        totalNumberLabel.setText(appointmentList.size() + " Appointment(s)");
    }

    @FXML
    private void OnClickShowTotalAppointments(ActionEvent actionEvent) throws SQLException {
        calculateTotalReports();
    }

    @FXML
    private void OnClickReturnToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 1000);
        CustomerRecordController customerRecord = fxmlLoader.getController();
        customerRecord.setUser(this.user);
        window.setScene(scene);
    }

    /**
     * This Lambda is grabbing ID from one list of objects and comparing it to a whole separate list of objects that
     * contain an ID.
     * @param actionEvent
     * @throws SQLException
     */
    @FXML
    private void OnContactSelectPopulateTableView(ActionEvent actionEvent) throws SQLException {
        ObservableList<Appointment> appointments = ClientQuery.getAllAppointments();
        List<Appointment> appointmentList = new ArrayList<>(appointments.stream()
                .filter(appointment -> appointment.getContactID() == contactsComboBox.getSelectionModel().getSelectedItem().getContactID())
                .toList());
        appointmentList.sort(Comparator.comparing(Appointment::getStart));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        contactTableView.setItems(FXCollections.observableArrayList(appointmentList));
        GuiUtils.autoResizeColumns(contactTableView);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
