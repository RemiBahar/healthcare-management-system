package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.cmd.hms.patient.model.Address;
 
public class AddressTest extends HttpRequestTest{
 

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
   

    // Integration tests

    @Test
	public void addAddress() throws Exception {
		String requestBody = "{\n  \"Street\": \"Richmond Road\", \"StreetNumber\": \"21\" \n, \"ZipCode\": \"TF54 5PH\", \"City\": \"Telford\", \"Description\": \"My home address\", \"Priority\": \"1\", \"Region\": \"Shropshire\",  \"PatientId\": \"1\", \"TypeId\": \"1\", \"CountryCode\": \"UK\"}";
        String endpoint = "/Addresss";
        addObject(requestBody, endpoint);
    }

    @Test
	public void minimalAddAddress() throws Exception {
        /*
         * Update according to minimal required fields
         */
		String requestBody = "{\n  \"Street\": \"Richmond Road\", \"StreetNumber\": \"30\" \n, \"City\": \"London\", \"CountryCode\": \"UK\"}";
        String endpoint = "/Addresss";
        addObject(requestBody, endpoint);
    }

    @Test
	public void invalidCountryAddress() throws Exception {
        // Attempt to update address with invalid country code
		String requestBody = "{\n  \"Street\": \"Dawlish Road\", \"StreetNumber\": \"1\" \n, \"ZipCode\": \"B29 7AU\", \"City\": \"Birmingham\", \"Description\": \"My other address\", \"Priority\": \"2\", \"Region\": \"West Midlands\",  \"PatientId\": \"1\", \"TypeId\": \"1\", \"CountryCode\": \"PK\"}";
        String endpoint = "/Addresss(1)";
        invalidUpdateObject(requestBody, endpoint); 
	}

    @Test
	public void invalidPatientAddress() throws Exception {
        // Attempt to update address with invalid values for PatientId
		String requestBody = "{\n  \"StreetNumber\": \"1\", \n\"PatientId\": \"10\"}";
        String endpoint = "/Addresss(1)";
        invalidUpdateObject(requestBody, endpoint);
	}

    @Test
	public void invalidTypeAddress() throws Exception {
        String requestBody = "{\n  \"Street\": \"Dawlish Road\", \n\"TypeId\": \"10\"}";	
        String endpoint = "/Addresss(1)";
        invalidUpdateObject(requestBody, endpoint);
	}

    /* Test validated fields */
    @Test
	public void missingStreetAddress() throws Exception {
        // Attempt to update address with invalid country code
		String requestBody = "{\n  \"Street\": null \n, \"ZipCode\": \"B29 7AU\"}";
        String endpoint = "/Addresss(1)";
        invalidUpdateObject(requestBody, endpoint); 
	}

    

    /* Test update */

    @Test
	public void updateAddress() throws Exception {
		String requestBody = "{\n  \"Street\": \"Dawlish Road\", \"StreetNumber\": \"1\" \n, \"ZipCode\": \"B29 7AU\", \"City\": \"Birmingham\", \"Region\": \"West Midlands\"}";
        String endpoint = "/Addresss(1)";

        updateObject(requestBody, endpoint);
	}


    @Test
	public void deleteAddress() throws Exception {
        deleteObject("/Addresss(2)");
    }
}