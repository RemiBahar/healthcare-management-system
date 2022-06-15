package com.cmd.hms.patient.model;

import javax.persistence.Embeddable;

public class Datatypes {
    public enum Title{
        MR,
        MRS,
        MISS,
        MS,
        DR,
        OTHER;
    }

    public enum Gender{
        UNKNOWN,
        MALE,
        FEMALE,
        OTHER;
    }

    public enum MaritalStatus{
        MARRIED,
        WIDOWED,
        SEPERATED,
        DIVORCED,
        SINGLE;
    }

    @Embeddable
    public static class Address{
        // Fields
        private String StreetNumber;
        private String Street;
        private String City;
        private String Zip;
        private String Country;

        // Getters and setters

        public String getStreetNumber() {
            return StreetNumber;   
        }
    
        public void setStreetNumber(String StreetNumber) {
            this.StreetNumber = StreetNumber;
        }

        public String getStreet() {
            return Street;   
        }
    
        public void setStreet(String Street) {
            this.Street = Street;
        }

        public String getCity() {
            return City;   
        }
    
        public void setCity(String City) {
            this.City = City;
        }

        public String getZip() {
            return Zip;   
        }
    
        public void setZip(String Zip) {
            this.Zip = Zip;
        }

        public String getCountry() {
            return Country;   
        }
    
        public void setCountry(String Country) {
            this.Country = Country;
        }


    }

    @Embeddable
    public static class Contact{
        // Fields
        private String Mobile;
        private String Email;

        // Getters and setters
        public String getMobile() {
            return Mobile;   
        }
    
        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getEmail() {
            return Email;   
        }
    
        public void setEmail(String Email) {
            this.Email = Email;
        }

    }
   
}
