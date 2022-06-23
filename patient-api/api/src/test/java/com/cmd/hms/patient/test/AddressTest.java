package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
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

    @Test
	public void addAddress() throws Exception {
		// Patient not finish being added unless you call it again here
		//this.addPatient();
		// Add object 
		String Street = "21";
		String StreetNumber = "Richmond Road";
		String ZipCode = "TF54 5PH";
		String City = "Telford";
		String Description = "My home address";
		String Priority = "1";
		String Region = "Shropshire";
		String PatientId = "1";
		String TypeId = "1";
		String CountryCode = "UK";

		String data = String.format(
			"{\n  \"Street\": \"%s\", \"StreetNumber\": \"%s\" \n, \"ZipCode\": \"%s\", \"City\": \"%s\", \"Description\": \"%s\", \"Priority\": \"%s\", \"Region\": \"%s\",  \"PatientId\": \"%s\", \"TypeId\": \"%s\", \"CountryCode\": \"%s\"}"
			,Street, StreetNumber, ZipCode, City, Description, Priority, Region, PatientId, TypeId, CountryCode);
		
		String url = BaseUrl + "/Addresss";
		
		String getUrl = postObject(data, url);

		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(Street, getJson.get("Street"));
		assertEquals(ZipCode, getJson.get("ZipCode"));
		assertEquals(City, getJson.get("City"));
		assertEquals(Description, getJson.get("Description"));
		assertEquals(Priority, getJson.get("Priority"));
		assertEquals(Region, getJson.get("Region"));
		assertEquals(PatientId, getJson.get("PatientId"));
		assertEquals(TypeId, getJson.get("TypeId"));
		assertEquals(CountryCode, getJson.get("CountryCode"));
	}

}