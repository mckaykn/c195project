package nielson.c195projectmkn.Models;

import java.sql.Timestamp;

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

        public int getId() {
            return id;
        }


        public String getCustomerName() {
            return customerName;
        }

        public String getAddress() {
            return address;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getPhone() {
            return phone;
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

        public Division getDivision() {
            return division;
        }
        public int getDivisionID(){
            return division.getID();
        }
        @Override
        public String toString(){
            return customerName;
        }
    }
