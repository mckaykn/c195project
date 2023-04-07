package nielson.c195projectmkn.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nielson.c195projectmkn.Models.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;


public abstract class ClientQuery {

    public static int insert(String fruitName, int colorId) throws SQLException {
        String sql = "INSERT INTO FRUITS (Fruit_Name, Color_ID) VALUES(?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int fruitId, String fruitName) throws SQLException {
        String sql = "UPDATE FRUITS SET Fruit_Name = ? WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, fruitId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int fruitId) throws SQLException {
        String sql = "DELETE FROM FRUITS WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, fruitId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int AppointmentID = rs.getInt("Appointment_ID");
            String AppointmentType = rs.getString("Type");
            System.out.print(AppointmentID + "|");
            System.out.print(AppointmentType + "\n");
        }

    }

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            int colorIdFK = rs.getInt("Color_ID");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + " |");
            System.out.print(colorIdFK + "\n");
        }

    }

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            Timestamp createDate = UTCtoLocalTimestamp(rs.getTimestamp("Create_Date"));
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = UTCtoLocalTimestamp(rs.getTimestamp("Last_Update"));
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            Division division = getDivisionById(divisionId);
            Customer customer = new Customer(id, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy, division);
            list.add(customer);
        }
        return list;
    }

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = UTCtoLocalTimestamp(rs.getTimestamp("Start"));
            Timestamp end = UTCtoLocalTimestamp(rs.getTimestamp("End"));
            Timestamp createDate = UTCtoLocalTimestamp(rs.getTimestamp("Create_Date"));
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = UTCtoLocalTimestamp(rs.getTimestamp("Last_Update"));
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            Customer customer = getCustomerByID(customerId);
            int userID = rs.getInt("User_ID");
            User user = getUserByID(userID);
            int contactID = rs.getInt("Contact_ID");
            Contact contact = getContactByID(contactID);
            Appointment appointment = new Appointment(id, title, description, location, type, start, end, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customer, user, contact);
            list.add(appointment);
        }
        return list;
    }

    public static Contact getContactByID(int contactID) throws SQLException {
        Contact contact = null;
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Contact> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            contact = new Contact(id, name, email);
            list.add(contact);
        }
        return contact;
    }

    public static User getUserByID(int userID) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        ObservableList<User> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String password = rs.getString("Password");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            user = new User(id, name, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            list.add(user);
        }
        return user;
    }

    private static Customer getCustomerByID(int customerID) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        if (rs.next()) {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            Timestamp createDate = (rs.getTimestamp("Create_Date"));
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = (rs.getTimestamp("Last_Update"));
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");
            Division division = getDivisionById(divisionId);
            customer = new Customer(id, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy, division);
            list.add(customer);
        }
        return customer;
    }

    public static Division getDivisionById(int divisionId) throws SQLException {
        Division division = null;
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");
            Country country = getCountryById(countryId);
            division = new Division(id, name, createDate, createdBy, lastUpdate, lastUpdateBy, country);
        }
        return division;
    }

    public static Country getCountryById(int countryId) throws SQLException {
        Country country = null;
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("Country_ID");
            String name = rs.getString("Country");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            country = new Country(id, name, createDate, createdBy, lastUpdate, lastUpdateBy);
        }
        return country;
    }

    public static User getUser(String userName) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            Date createDate = rs.getDate("Create_Date");
            String password = rs.getString("Password");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            user = new User(id, name, password, createDate, createdBy, lastUpdate, lastUpdateBy);
        }
        return user;
    }

    public static ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> divisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");
            Country country = getCountryById(countryId);
            Division division = new Division(id, name, createDate, createdBy, lastUpdate, lastUpdateBy, country);
            divisions.add(division);
        }
        return divisions;

    }

    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Country_ID");
            String name = rs.getString("Country");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            Country country = new Country(id, name, createDate, createdBy, lastUpdate, lastUpdateBy);
            countries.add(country);
        }
        return countries;
    }

    public static void SaveCustomer(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, " +
                "Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, newCustomer.getCustomerName());
        ps.setString(2, newCustomer.getAddress());
        ps.setString(3, newCustomer.getPostalCode());
        ps.setString(4, newCustomer.getPhone());
        ps.setTimestamp(5, timestampToUTC(new Timestamp(newCustomer.getCreateDate().getTime())));
        ps.setString(6, newCustomer.getCreatedBy());
        ps.setTimestamp(7, timestampToUTC(new Timestamp(newCustomer.getLastUpdate().getTime())));
        ps.setString(8, newCustomer.getLastUpdatedBy());
        ps.setInt(9, newCustomer.getDivisionID());
        ps.executeUpdate();
    }

    public static Timestamp timestampToUTC(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        ZonedDateTime utcStartDateTime = localDateTime.atZone(ZoneOffset.systemDefault()).withZoneSameInstant(ZoneOffset.UTC);

        return Timestamp.valueOf(utcStartDateTime.toLocalDateTime());
    }

    public static Timestamp UTCtoLocalTimestamp(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        ZonedDateTime utcStartDateTime = localDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault());

        return Timestamp.valueOf(utcStartDateTime.toLocalDateTime());
    }

    public static void SaveAppointment(Appointment newAppointment) throws SQLException, ParseException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type," +
                "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, newAppointment.getTitle());
        ps.setString(2, newAppointment.getDescription());
        ps.setString(3, newAppointment.getLocation());
        ps.setString(4, newAppointment.getType());
        ps.setTimestamp(5, timestampToUTC(newAppointment.getStart()));
        ps.setTimestamp(6, timestampToUTC(newAppointment.getEnd()));
        ps.setTimestamp(7, timestampToUTC(newAppointment.getCreateDate()));
        ps.setString(8, newAppointment.getCreatedBy());
        ps.setTimestamp(9, timestampToUTC(newAppointment.getLastUpdate()));
        ps.setString(10, newAppointment.getLastUpdatedBy());
        ps.setInt(11, newAppointment.getCustomerID());
        ps.setInt(12, newAppointment.getUserID());
        ps.setInt(13, newAppointment.getContactID());
        ps.executeUpdate();
    }

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Contact> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact contact = new Contact(id, name, email);
            list.add(contact);
        }
        return list;
    }

    public static ObservableList<Appointment> getAllAppointmentsByWeek() throws SQLException {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(startOfWeek));
        ps.setDate(2, Date.valueOf(endOfWeek));
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            Customer customer = getCustomerByID(customerId);
            int userID = rs.getInt("User_ID");
            User user = getUserByID(userID);
            int contactID = rs.getInt("Contact_ID");
            Contact contact = getContactByID(contactID);
            Appointment appointment = new Appointment(id, title, description, location, type, start, end, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customer, user, contact);
            list.add(appointment);
        }
        return list;
    }

    public static ObservableList<Appointment> getAllAppointmentsByMonth() throws SQLException {
        LocalDate today = LocalDate.now();
        YearMonth yearMonth = YearMonth.from(today);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(firstDayOfMonth));
        ps.setDate(2, Date.valueOf(lastDayOfMonth));
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            Customer customer = getCustomerByID(customerId);
            int userID = rs.getInt("User_ID");
            User user = getUserByID(userID);
            int contactID = rs.getInt("Contact_ID");
            Contact contact = getContactByID(contactID);
            Appointment appointment = new Appointment(id, title, description, location, type, start, end, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customer, user, contact);
            list.add(appointment);
        }
        return list;
    }

    public static ObservableList<Appointment> getAppointmentsWithin15Minutes() throws SQLException {
        String sql = "SELECT * FROM appointments\n" +
                "WHERE Start BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 15 MINUTE);";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdateBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            Customer customer = getCustomerByID(customerId);
            int userID = rs.getInt("User_ID");
            User user = getUserByID(userID);
            int contactID = rs.getInt("Contact_ID");
            Contact contact = getContactByID(contactID);
            Appointment appointment = new Appointment(id, title, description, location, type, start, end, createDate,
                    createdBy, lastUpdate, lastUpdateBy, customer, user, contact);
            list.add(appointment);
        }
        return list;
    }

}
