package nielson.c195projectmkn.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nielson.c195projectmkn.Main;
import nielson.c195projectmkn.Models.Appointment;
import nielson.c195projectmkn.Models.Contact;
import nielson.c195projectmkn.Models.Customer;
import nielson.c195projectmkn.Models.User;
import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAppointmentFormController  implements Initializable {
    @FXML
    private TextField appointmentTitleTextField;
    @FXML
    private TextField appointmentDescriptionTextField;
    @FXML
    private TextField appointmentLocationTextField;
    @FXML
    private ComboBox<Contact> appointmentContactComboBox;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<Customer> appointmentCustomerComboBox;
    @FXML
    private TextField appointmentTypeTextField;
    @FXML
    private DatePicker appointmentStartDatePicker;
    @FXML
    private ComboBox<String> appointmentStartTimeComboBox;
    @FXML
    private DatePicker appointmentEndDatePicker;
    @FXML
    private ComboBox<String> appointmentEndTimeComboBox;

    private static final String[] HOUR_OPTIONS = {"00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00",
            "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00",
            "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00"};
    private Appointment appointment;
    @FXML
    private Button updateAppointmentButton;
    private User user;


    @FXML
    private void OnClickUpdateAppointment(ActionEvent actionEvent) throws SQLException, IOException {

        LocalDate startDate = LocalDate.parse(appointmentStartDatePicker.getValue().toString());
        LocalTime startTime = LocalTime.parse(appointmentStartTimeComboBox.getValue());
        LocalDate endDate = LocalDate.parse(appointmentEndDatePicker.getValue().toString());
        LocalTime endTime = LocalTime.parse(appointmentEndTimeComboBox.getValue());
        LocalDateTime dateTimeStart = LocalDateTime.of(startDate, startTime);
        Timestamp startTimeStamp = Timestamp.valueOf(dateTimeStart);
        LocalDateTime dateTimeEnd = LocalDateTime.of(endDate, endTime);
        Timestamp endTimeStamp = Timestamp.valueOf(dateTimeEnd);

        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointmentTitleTextField.getText());
        ps.setString(2, appointmentDescriptionTextField.getText());
        ps.setString(3, appointmentLocationTextField.getText());
        ps.setString(4, appointmentTypeTextField.getText());
        ps.setTimestamp(5, startTimeStamp);
        ps.setTimestamp(6, endTimeStamp);
        ps.setTimestamp(7, appointment.getCreateDate());
        ps.setString(8, appointment.getCreatedBy());
        ps.setTimestamp(9, appointment.getLastUpdate());
        ps.setString(10, appointment.getLastUpdatedBy());
        ps.setInt(11, appointment.getCustomerID());
        ps.setInt(12, appointment.getUserID());
        ps.setInt(13, appointment.getContactID());
        ps.setInt(14, appointment.getID());
        ps.executeUpdate();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) updateAppointmentButton.getScene().getWindow();
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
    public void setUser(User user){
        this.user = user;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointmentStartTimeComboBox.getItems().addAll(HOUR_OPTIONS);
            appointmentEndTimeComboBox.getItems().addAll(HOUR_OPTIONS);
            appointmentContactComboBox.getItems().setAll(ClientQuery.getAllContacts());
            appointmentCustomerComboBox.getItems().setAll(ClientQuery.getAllCustomers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadAppointment(Appointment selectedAppointment) throws SQLException {
        //Set Start-Date DatePicker to correct Value and set TimeComboBox to correct Value
        LocalDateTime localStartDateTime = selectedAppointment.getStart().toLocalDateTime();
        int startYear = localStartDateTime.getYear();
        Month startMonthLetters = localStartDateTime.getMonth();
        int startDayOfMonth = localStartDateTime.getDayOfMonth();
        int startMonthInt = startMonthLetters.getValue();
        String startDayOfMonthNew;
        if (startDayOfMonth < 10){
            startDayOfMonthNew = "0" + startDayOfMonth;
        }
        else {
            startDayOfMonthNew = String.valueOf(startDayOfMonth);
        }
        if (startMonthInt < 10) {
            String startDate = (startYear + "/0" + startMonthInt + "/" + startDayOfMonthNew);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
            LocalDate localDateStart = LocalDate.parse(startDate, dtf);
            appointmentStartDatePicker.setValue(localDateStart);
        }
        else {
            String startDate = (startYear + "/" + startMonthInt + "/" + startDayOfMonthNew);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
            LocalDate localDateStart = LocalDate.parse(startDate, dtf);
            appointmentStartDatePicker.setValue(localDateStart);
        }
        int startHour = localStartDateTime.getHour();
        String startTime;
        if (startHour < 10){
            startTime = "0" + startHour + ":00:00";
        }
        else {
            startTime = startHour + ":00:00";
        }
        appointmentStartTimeComboBox.getSelectionModel().select(startTime);
        //Set End-Date DatePicker to correct Value and End-Time ComboBoxes
        LocalDateTime localEndDateTime = selectedAppointment.getEnd().toLocalDateTime();
        int endYear = localEndDateTime.getYear();
        Month endMonthLetters = localEndDateTime.getMonth();
        int endDayOfMonth = localEndDateTime.getDayOfMonth();
        String endDayOfMonthNew;
        if (endDayOfMonth < 10){
            endDayOfMonthNew = "0" + endDayOfMonth;
        }
        else {
            endDayOfMonthNew = String.valueOf(endDayOfMonth);
        }
        int endMonthInt = endMonthLetters.getValue();
        if (endMonthInt < 10) {
            String endDate = (endYear + "/0" + endMonthInt + "/" + endDayOfMonthNew);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
            LocalDate localDateEnd = LocalDate.parse(endDate, dtf);
            appointmentEndDatePicker.setValue(localDateEnd);
        }
        else {
            String endDate = (endYear + "/" + endMonthInt + "/" + endDayOfMonthNew);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/dd/MM");
            LocalDate localDateEnd = LocalDate.parse(endDate, dtf);
            appointmentEndDatePicker.setValue(localDateEnd);
        }
        int endHour = localEndDateTime.getHour();
        if (endHour < 10){
            String endTime = "0" + endHour + ":00:00";
            appointmentEndTimeComboBox.getSelectionModel().select(endTime);

        }
        else {
            String endTime = endHour + ":00:00";
            appointmentEndTimeComboBox.getSelectionModel().select(endTime);

        }

        appointmentTitleTextField.setText(selectedAppointment.getTitle());
        appointmentDescriptionTextField.setText(selectedAppointment.getDescription());
        appointmentLocationTextField.setText(selectedAppointment.getLocation());
        appointmentTypeTextField.setText(selectedAppointment.getType());
        appointmentCustomerComboBox.getSelectionModel().select(selectedAppointment.getCustomer());
        appointmentContactComboBox.getSelectionModel().select(selectedAppointment.getContact());

    }
}
