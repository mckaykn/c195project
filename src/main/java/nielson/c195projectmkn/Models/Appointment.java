package nielson.c195projectmkn.Models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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

    public Appointment() {

    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCustomerID() {
        return customer.getId();
    }

    public String getContactName(){
        return contact.getName();
    }

    public int getUserID() {
        return user.getID();
    }

    public int getContactID() {
        return contact.getContactID();
    }

    public Contact getContact() {
        return contact;
    }
}

