package nielson.c195projectmkn.Controllers;

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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddAppointmentFormController implements Initializable {
    @FXML
    private TextField appointmentTitleTextField;
    @FXML
    private TextField appointmentDescriptionTextField;
    @FXML
    private TextField appointmentLocationTextField;
    @FXML
    private ComboBox<Contact> appointmentContactComboBox;
    @FXML
    private Button createAppointmentButton;
    @FXML
    private Button backButton;
    private Appointment newAppointment;
    @FXML
    private DatePicker appointmentStartDatePicker;
    @FXML
    private ComboBox<String> appointmentStartTimeComboBox;
    @FXML
    private DatePicker appointmentEndDatePicker;
    @FXML
    private ComboBox<String> appointmentEndTimeComboBox;
    @FXML
    private TextField appointmentTypeTextField;
    @FXML
    private ComboBox<Customer> appointmentCustomerComboBox;
    private User user;
    private static final String[] HOUR_OPTIONS = {"00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00",
            "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00",
            "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00"};

    public void setUser(User user){
        this.user = user;
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
        appointmentContactComboBox.setCellFactory(new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> param) {
                return new ListCell<Contact>() {
                    protected void updateItem(Contact item, boolean empty) {
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
        appointmentCustomerComboBox.setCellFactory(new Callback<ListView<Customer>, ListCell<Customer>>() {
            @Override
            public ListCell<Customer> call(ListView<Customer> param) {
                return new ListCell<Customer>() {
                    protected void updateItem(Customer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getCustomerName()); // Replace "getProperty()" with the actual property getter method name
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void OnClickSaveAppointment(ActionEvent actionEvent) throws IOException, SQLException, ParseException {
        LocalDate startDate = LocalDate.parse(appointmentStartDatePicker.getValue().toString());
        LocalTime startTime = LocalTime.parse(appointmentStartTimeComboBox.getValue());
        LocalDate endDate = LocalDate.parse(appointmentEndDatePicker.getValue().toString());
        LocalTime endTime = LocalTime.parse(appointmentEndTimeComboBox.getValue());

        LocalDateTime dateTimeStart = LocalDateTime.of(startDate, startTime);
        Timestamp startTimeStamp = Timestamp.valueOf(dateTimeStart);

        LocalDateTime dateTimeEnd = LocalDateTime.of(endDate, endTime);
        Timestamp endTimeStamp = Timestamp.valueOf(dateTimeEnd);

        newAppointment = new Appointment(0, appointmentTitleTextField.getText(),
                appointmentDescriptionTextField.getText(),
                appointmentLocationTextField.getText(),
                appointmentTypeTextField.getText(),
                startTimeStamp,
                endTimeStamp,
                new Timestamp(Calendar.getInstance().getTime().getTime()),
                user.getName(),
                new Timestamp(Calendar.getInstance().getTime().getTime()),
                user.getName(),
                appointmentCustomerComboBox.getSelectionModel().getSelectedItem(),
                user,
                appointmentContactComboBox.getSelectionModel().getSelectedItem());
        ClientQuery.SaveAppointment(newAppointment);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) createAppointmentButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        CustomerRecordController customerRecordController = fxmlLoader.getController();
        customerRecordController.setUser(this.user);
        window.setScene(scene);
    }

    @FXML
    private void OnClickReturnToMain(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CustomerRecord.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        window.setScene(scene);
    }
}
