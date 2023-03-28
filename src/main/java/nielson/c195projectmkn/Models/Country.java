package nielson.c195projectmkn.Models;

import java.util.Date;

public class Country {
    private int ID;
    private String name;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    public Country(int ID, String name, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
        this.ID = ID;
        this.name = name;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    @Override
    public String toString(){
        return name;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
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
}
