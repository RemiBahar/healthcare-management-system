package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Address;
 
public class AddressTest {
 

    // Instanstiate object
    Address address = new Address();

    // Tests
    @Test
    public void AddressIdTest(){
        Long AddressId = 1L;
        address.setAddressId(AddressId);
            
        assertTrue(address.getAddressId().equals(AddressId), "AddressId");
    }

    @Test
    public void AddressStreetNumberTest(){
        String StreetNumber = "277";
        address.setStreetNumber(StreetNumber);
            
        assertTrue(address.getStreetNumber().equals(StreetNumber), "StreetNumber");
    }

    @Test
    public void AddressStreetTest(){
        String Street = "Evergreen Terrace";
        address.setStreet(Street);
            
        assertTrue(address.getStreet().equals(Street), "Street");
    }

    @Test
    public void AddressZipCodeTest(){
        String ZipCode = "B29 7AU";
        address.setZipCode(ZipCode);
            
        assertTrue(address.getZipCode().equals(ZipCode), "ZipCode");
    }

    @Test
    public void AddressCityTest(){
        String City = "Springfield";
        address.setCity(City);
            
        assertTrue(address.getCity().equals(City), "City");
    }

    @Test
    public void AddressDescriptionTest(){
        String Description = "Home"; 
        address.setDescription(Description);
            
        assertTrue(address.getDescription().equals(Description), "Description");
    }

    @Test
    public void AddressPriorityTest(){
        Long Priority = 1L;
        address.setPriority(Priority);
            
        assertTrue(address.getPriority().equals(Priority), "Priority");
    }

    @Test
    public void AddressRegionTest(){
        String Region = "Flordia";
        address.setRegion(Region);
            
        assertTrue(address.getRegion().equals(Region), "Region");
    }

    @Test
    public void AddressPatientIdTest(){
        Long PatientId = 1L;
        address.setPatientId(PatientId);
            
        assertTrue(address.getPatientId().equals(PatientId), "Patient");
    }

    @Test
    public void AddressTypeIdTest(){
        Long TypeId = 1L;
        address.setTypeId(TypeId);
            
        assertTrue(address.getTypeId().equals(TypeId), "TypeId");
    }

    @Test
    public void AddressCountryCodeTest(){
        String CountryCode = "UK";
        address.setCountryCode(CountryCode);
            
        assertTrue(address.getCountryCode().equals(CountryCode), "CountryCode");
    }

}