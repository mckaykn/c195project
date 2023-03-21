package nielson.c195projectmkn.Models;

import java.util.Date;

    public class Division {
        private int ID;
        private String name;
        private Date createDate;
        private String createdBy;
        private Date lastUpdate;
        private String lastUpdatedBy;
        private Country country;

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

        public int getID() {
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

        public Country getCountry() {
            return country;
        }
    }


