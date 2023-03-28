package nielson.c195projectmkn.Models;

import java.util.Date;

public class Appointment {
    private int ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private Customer customer;
    private User user;
    private Contact contact;

    public Appointment(int ID, String title, String description, String location, String type, Date start, Date end, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy, Customer customer, User user, Contact contact) {
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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getLastUpdate() {
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

    public int getUserID() {
        return user.getID();
    }

    public int getContactID() {
        return contact.getContactID();
    }
}

