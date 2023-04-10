package nielson.c195projectmkn.Models;

import java.util.Date;

/**
 * @author mckaykn
 * This class represents a user by ID, name, password, createDate, CreatedBy, lastUpdate, and lastUpdatedBy
 */
public class User {
    private int ID;
    private String name;
    private String password;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    /**
     * Creates a new User object with the given parameters.
     *
     * @param ID           the ID of the user.
     * @param name         the name of the user.
     * @param password     the password of the user.
     * @param createDate   the date the user was created.
     * @param createdBy    the creator of the user.
     * @param lastUpdate   the date the user was last updated.
     * @param lastUpdatedBy the user who last updated the user.
     */
    public User(int ID, String name, String password, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    @Override
    public String toString(){
        return name;
    }

    /**
     * Gets the ID of the user.
     *
     * @return the ID of the user.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the user.
     *
     * @param ID the ID of the user.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the date the user was created.
     *
     * @return the date the user was created.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the user was created.
     *
     * @param createDate the date the user was created.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the creator of the user.
     *
     * @return the creator of the user.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the creator of the user.
     *
     * @param createdBy the creator of the user.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getLastUpdated(Date lastUpdate) {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    public String getLastUpdatedBy(String lastUpdatedBy) {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = lastUpdatedBy;
    }

}