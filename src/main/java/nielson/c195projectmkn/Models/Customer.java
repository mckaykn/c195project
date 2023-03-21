package nielson.c195projectmkn.Models;

import java.util.Date;

    public class Customer {
        private int ID;
        private String customerName;
        private String address;
        private String postalCode;
        private String phone;
        private Date createDate;
        private String createdBy;
        private Date lastUpdate;
        private Division division;

        public Customer(int ID, String customerName, String address, String postalCode, String phone,
                        Date createDate, String createdBy, Date lastUpdate, Division division) {
            this.ID = ID;
            this.customerName = customerName;
            this.address = address;
            this.postalCode = postalCode;
            this.phone = phone;
            this.createDate = createDate;
            this.createdBy = createdBy;
            this.lastUpdate = lastUpdate;
            this.division = division;
        }

        public int getID() {
            return ID;
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

        public Date getCreateDate() {
            return createDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public Date getLastUpdate() {
            return lastUpdate;
        }

        public Division getDivision() {
            return division;
        }
    }
