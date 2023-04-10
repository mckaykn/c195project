package nielson.c195projectmkn.Models;

import java.util.Date;
/**
 @author mckaykn
 The Division class represents a division in a company, with information about its ID, name, creation date, creator, last update date,

 last updater and the country to which it belongs.
 */
public class Division {
    private int ID;
    private String name;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private Country country;
/**

 Creates a new Division object with the specified ID, name, creation date, creator, last update date, last updater and country.
 @param ID the ID of the division
 @param name the name of the division
 @param createDate the date when the division was created
 @param createdBy the name of the person who created the division
 @param lastUpdate the date when the division was last updated
 @param lastUpdatedBy the name of the person who last updated the division
 @param country the country to which the division belongs
 */

    public Division(int ID, String name, Date createDate, String createdBy,
                    Date lastUpdate, String lastUpdatedBy, Country country) {
        this.ID = ID;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.country = country;
    }
/**

 Returns a String representation of the division object.
 @return the name of the division
 */
    @Override
    public String toString(){
        return name;
    }
/**

 Returns the ID of the division.
 @return the ID of the division
 */

    public int getID() {
        return ID;
    }
/**

 Returns the name of the division.
 @return the name of the division
 */
    public String getName() {
        return name;
    }
/**

 Returns the date when the division was created.
 @return the date when the division was created
 */
    public Date getCreateDate() {
        return createDate;
    }
/**

 Returns the name of the person who created the division.
 @return the name of the person who created the division
 */
    public String getCreatedBy() {
        return createdBy;
    }
/**

 Returns the date when the division was last updated.
 @return the date when the division was last updated
 */
    public Date getLastUpdate() {
        return lastUpdate;
    }
/**

 Returns the name of the person who last updated the division.
 @return the name of the person who last updated the division
 */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
/**

 Returns the country to which the division belongs.
 @return the country to which the division belongs
 */
    public Country getCountry() {
        return country;
    }
}


