package nielson.c195projectmkn.Models;

import java.sql.Timestamp;
/**

 The Customer class represents a customer, including its unique identifier, name, address,
 postal code, phone number, creation date, creator, last update date, last updater, and division.
 */
public class Customer {
    private int id;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private Division division;

    /**

    Constructs a new Customer object with the given ID, name, address, postal code, phone number,
    creation date, creator, last update date, last updater, and division.
    @param id the unique identifier for the customer
    @param customerName the name of the customer
    @param address the address of the customer
    @param postalCode the postal code of the customer
    @param phone the phone number of the customer
    @param createDate the date the customer was created
    @param createdBy the creator of the customer
    @param lastUpdate the date the customer was last updated
    @param lastUpdatedBy the last updater of the customer
    @param division the division the customer belongs to
    */
    public Customer(int id, String customerName, String address, String postalCode, String phone,
                   Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, Division division) {
        super();
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.division = division;
    }
    /**

     Returns the unique identifier for this country.
     @return the unique identifier for this country
     */
    public int getId() {
        return id;
    }
    /**

     Returns the name of the customer.
     @return the name of the customer
     */

    public String getCustomerName() {
        return customerName;
    }
    /**

     Returns the address of the customer.
     @return the address of the customer
     */
    public String getAddress() {
        return address;
    }
    /**

     Returns the postal code of the customer's address.
     @return the postal code of the customer's address
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**

     Returns the phone number of the customer.
     @return the phone number of the customer
     */
    public String getPhone() {
        return phone;
    }
    /**

     Returns the date and time when the customer was created.
     @return the date and time when the customer was created
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    /**

     Returns the name of the user who created the customer.
     @return the name of the user who created the customer
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**

     Returns the date and time when the customer was last updated.
     @return the date and time when the customer was last updated
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    /**

     Returns the name of the user who last updated the customer.
     @return a string representing the name of the user who last updated the customer
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**

     Returns the division to which the customer belongs.
     @return a Division object representing the division to which the customer belongs
     */

    public Division getDivision() {
        return division;
    }
    /**

     Returns the ID of the division to which the customer belongs.
     @return an integer representing the ID of the division to which the customer belongs
     */
    public int getDivisionID(){
        return division.getID();
    }
    /**

     Returns a string representation of the customer object.
     @return a string representing the name of the customer
     */
    @Override
    public String toString(){
        return customerName;
    }
}
