package nielson.c195projectmkn.Models;

import java.time.LocalDateTime;

public class Customer {
        private int id;
        private String customerName;
        private String address;
        private String postalCode;
        private String phone;
        private LocalDateTime createDate;
        private String createdBy;
        private LocalDateTime lastUpdate;
        private String lastUpdatedBy;
        private Division division;

        public Customer(int id, String customerName, String address, String postalCode, String phone,
                       LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, Division division) {
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

        public LocalDateTime getCreateDate() {
            return createDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public LocalDateTime getLastUpdate() {
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
    }
