package nielson.c195projectmkn.Models;

import java.util.Date;

public class Country {
    private int ID;
    private String name;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    /**

     Constructs a new Country object with the given ID, name, creation date, creator,
     last update date, and last updater.
     @param ID the unique identifier for the country
     @param name the name of the country
     @param createDate the date the country was created
     @param createdBy the creator of the country
     @param lastUpdate the date the country was last updated
     @param lastUpdatedBy the last updater of the country
     */
    public Country(int ID, String name, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
        this.ID = ID;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**

     Returns the name of the country as a string representation of the object.
     @return the name of the country
     */
    @Override
    public String toString(){
        return name;
    }
    /**

     Returns the unique identifier for this country.
     @return the unique identifier for this country
     */
    public int getId() {
        return ID;
    }
    /**

     Returns the name of the country.
     @return the name of the country
     */
    public String getName() {
        return name;
    }
    /**

     Returns the date the country was created.
     @return the date the country was created
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**

     Returns the creator of the country.
     @return the creator of the country
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**

     Returns the date the country was last updated.
     @return the date the country was last updated
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }
    /**

     Returns the last updater of the country.
     @return the last updater of the country
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
