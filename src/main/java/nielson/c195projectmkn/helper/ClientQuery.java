package nielson.c195projectmkn.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nielson.c195projectmkn.Models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


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
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            LocalDateTime createDate = convertDateToLocalDateTime(rs.getDate("Create_Date"));
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = convertDateToLocalDateTime(rs.getDate("Last_Update"));
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
            Date start = rs.getDate("Start");
            Date end = rs.getDate("End");
            Date createDate = rs.getDate("Create_Date");
            String createdBy = rs.getString("Created_By");
            Date lastUpdate = rs.getDate("Last_Update");
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
            LocalDateTime createDate = convertDateToLocalDateTime(rs.getDate("Create_Date"));
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = convertDateToLocalDateTime(rs.getDate("Last_Update"));
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
        LocalDateTime dateValue1 = newCustomer.getCreateDate(); // your LocalDateTime

        java.util.Date utilDate;
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern(dateFormat);
        SimpleDateFormat sdf1 = new SimpleDateFormat(dateFormat);
        try {
            utilDate = sdf1.parse(dateValue1.format(dtf1));
        } catch (ParseException e) {
            utilDate = null; // handle the exception
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        LocalDateTime dateValue2 = newCustomer.getCreateDate(); // your LocalDateTime
        try {
            utilDate = sdf1.parse(dateValue2.format(dtf1));
        } catch (ParseException e) {
            utilDate = null; // handle the exception
        }
        java.sql.Date sqlDate2 = new java.sql.Date(utilDate.getTime());

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, newCustomer.getCustomerName());
        ps.setString(2, newCustomer.getAddress());
        ps.setString(3, newCustomer.getPostalCode());
        ps.setString(4, newCustomer.getPhone());
        ps.setDate(5, sqlDate);
        ps.setDate(6, sqlDate2);
        ps.setString(7, newCustomer.getLastUpdatedBy());
        ps.setInt(8, newCustomer.getDivisionID());
        ps.executeUpdate();
    }


}
