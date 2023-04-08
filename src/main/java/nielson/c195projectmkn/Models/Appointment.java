package nielson.c195projectmkn.Models;

import java.sql.Timestamp;

public class Appointment {
    private int ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private Customer customer;
    private User user;
    private Contact contact;

    /**
     * Constructs an Appointment object with the specified properties.
     * @param ID the ID of the appointment
     * @param title the title of the appointment
     * @param description the description of the appointment
     * @param location the location of the appointment
     * @param type the type of the appointment
     * @param start the start timestamp of the appointment
     * @param end the end timestamp of the appointment
     * @param createDate the creation date of the appointment
     * @param createdBy the user who created the appointment
     * @param lastUpdate the timestamp of the last update to the appointment
     * @param lastUpdatedBy the user who last updated the appointment
     * @param customer the customer associated with the appointment
     * @param user the user associated with the appointment
     * @param contact the contact associated with the appointment
     */
    public Appointment(int ID, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, Customer customer, User user, Contact contact) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customer = customer;
        this.user = user;
        this.contact = contact;
    }

    /**
     * Constructs an empty Appointment object.
     */
    public Appointment() {

    }

    /**
     * Returns the ID of the appointment.
     * @return the ID of the appointment
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the title of the appointment.
     * @return the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the appointment.
     * @return the description of the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the location of the appointment.
     * @return the location of the appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the type of the appointment.
     * @return the type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the start timestamp of the appointment.
     * @return the start timestamp of the appointment
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * Returns the end timestamp of the appointment.
     * @return the end timestamp of the appointment
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
    * Returns the creation date of the appointment.
    * @return the creation date of the appointment.
    */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * returns the user who created this appointment.
     * @return the user who created this appointment.
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * returns the Timestamp of when the appointment was last updated.
     * @return the Timestamp of when the appointment was last updated.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**
     * returns the user who last updated this customer.
     * @return the Timestamp of when the appointment was last updated.
     */

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * returns the customer associated with the appointment.
     * @return the customer associated with the appointment.
     */
    public Customer getCustomer() {
        return customer;
    }
    /**
     * returns the Customer ID of the customer associated with the appointment.
     * @return the Customer ID of the customer associated with the appointment.
     */

    public int getCustomerID() {
        return customer.getId();
    }
    /**
     * returns the ContactName of the contact associated with the appointment.
     * @return the ContactName of the contact associated with the appointment.
     */


    public String getContactName(){
        return contact.getName();
    }
    /**
     * returns the user associated with the appointment.
     * @return the user associated with the appointment.
     */

    public User getUser() {
        return this.user;
    }
    /**
     * returns the userID associated with the appointment.
     * @return the userID associated with the appointment.
     */

    public int getUserID() {
        return user.getID();
    }
    /**
     * returns the ContactID of the contact associated with the appointment.
     * @return the ContactID of the contact associated with the appointment.
     */


    public int getContactID() {
        return contact.getContactID();
    }
    /**
     * returns the Contact of the contact associated with the appointment.
     * @return the Contact of the contact associated with the appointment.
     */

    public Contact getContact() {
        return contact;
    }
}

