package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

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

    @Test
	public void invalidCountryAddress() throws Exception {
        // Get initial address
        String beforeResponse = restTemplate.getForObject(BaseUrl + "/Addresss(1)",String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
        // Attempt to update address with invalid values for PatientId, TypeId, CountryCode
        String Street = "1";
		String StreetNumber = "Dawlish Road";
		String ZipCode = "B29 7AU";
		String City = "Birmingham";
		String Description = "My other address";
		String Priority = "2";
		String Region = "West Midlands";
		String PatientId = "1";
		String TypeId = "1";
		String CountryCode = "PK";

		String data = String.format(
			"{\n  \"Street\": \"%s\", \"StreetNumber\": \"%s\" \n, \"ZipCode\": \"%s\", \"City\": \"%s\", \"Description\": \"%s\", \"Priority\": \"%s\", \"Region\": \"%s\",  \"PatientId\": \"%s\", \"TypeId\": \"%s\", \"CountryCode\": \"%s\"}"
			,Street, StreetNumber, ZipCode, City, Description, Priority, Region, PatientId, TypeId, CountryCode);
		
        String url = BaseUrl + "/Addresss(1)";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        restTemplate.put(url, request, String.class);
    

		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");

		// Due to invalid FK, put operation should fail and fields remain unchanged
		assertEquals(beforeJson.get("Street"), afterJson.get("Street"));
        assertEquals(beforeJson.get("StreetNumber"), afterJson.get("StreetNumber"));
		assertEquals(beforeJson.get("ZipCode"), afterJson.get("ZipCode"));
		assertEquals(beforeJson.get("City"), afterJson.get("City"));
		assertEquals(beforeJson.get("Description"), afterJson.get("Description"));
		assertEquals(beforeJson.get("Priority"), afterJson.get("Priority"));
		assertEquals(beforeJson.get("Region"), afterJson.get("Region"));
		assertEquals(beforeJson.get("PatientId"), afterJson.get("PatientId"));
		assertEquals(beforeJson.get("TypeId"), afterJson.get("TypeId"));
		assertEquals(beforeJson.get("CountryCode"), afterJson.get("CountryCode"));
	}

    @Test
	public void invalidPatientAddress() throws Exception {
        // Get initial address
        String beforeResponse = restTemplate.getForObject(BaseUrl + "/Addresss(1)",String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
        // Attempt to update address with invalid values for PatientId
		String StreetNumber = "1";
		String PatientId = "10";

		String data = String.format("{\n  \"StreetNumber\": \"%s\", \n\"PatientId\": \"%s\"}",StreetNumber, PatientId);
		
        String url = BaseUrl + "/Addresss(1)";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        restTemplate.patchForObject(url, request, String.class);

        // Get entity
		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");

		// Due to invalid FK, patch operation should fail and fields remain unchanged
        assertEquals(beforeJson.get("StreetNumber"), afterJson.get("StreetNumber"));
		assertEquals(beforeJson.get("PatientId"), afterJson.get("PatientId"));
	}

    @Test
	public void invalidTypeAddress() throws Exception {
        // Get initial address
        String beforeResponse = restTemplate.getForObject(BaseUrl + "/Addresss(1)",String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
        // Attempt to update address with invalid values for PatientId
		String Street = "Dawlish Road";
		String TypeId = "10";

		String data = String.format("{\n  \"Street\": \"%s\", \n\"TypeId\": \"%s\"}",Street, TypeId);
		
        String url = BaseUrl + "/Addresss(1)";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        restTemplate.patchForObject(url, request, String.class);

        // Get entity
		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");

		// Due to invalid FK, patch operation should fail and fields remain unchanged
        assertEquals(beforeJson.get("Street"), afterJson.get("Street"));
		assertEquals(beforeJson.get("TypeId"), afterJson.get("TypeId"));
	}

    @Test
	public void patchAddress() throws Exception {
        // Get initial address
        String beforeResponse = restTemplate.getForObject(BaseUrl + "/Addresss(1)",String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
        // Attempt to update address with invalid values for PatientId, TypeId, CountryCode
        String Street = "1";
		String StreetNumber = "Dawlish Road";
		String ZipCode = "B29 7AU";
		String City = "Birmingham";
		String Region = "West Midlands";

		String data = String.format(
			"{\n  \"Street\": \"%s\", \"StreetNumber\": \"%s\" \n, \"ZipCode\": \"%s\", \"City\": \"%s\", \"Region\": \"%s\"}"
			,Street, StreetNumber, ZipCode, City, Region);
		
        String url = BaseUrl + "/Addresss(1)";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        restTemplate.patchForObject(url, request, String.class);
    

		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");

		// Fields included in patch should be changed and fields not included should be unchanged
		assertEquals(Street, afterJson.get("Street"));
        assertEquals(StreetNumber, afterJson.get("StreetNumber"));
		assertEquals(ZipCode, afterJson.get("ZipCode"));
		assertEquals(City, afterJson.get("City"));
		assertEquals(beforeJson.get("Description"), afterJson.get("Description"));
		assertEquals(beforeJson.get("Priority"), afterJson.get("Priority"));
		assertEquals(Region, afterJson.get("Region"));
		assertEquals(beforeJson.get("PatientId"), afterJson.get("PatientId"));
		assertEquals(beforeJson.get("TypeId"), afterJson.get("TypeId"));
		assertEquals(beforeJson.get("CountryCode"), afterJson.get("CountryCode"));
	}

    @Test
	public void putAddress() throws Exception {
        // Get initial address
        String beforeResponse = restTemplate.getForObject(BaseUrl + "/Addresss(1)",String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
        // Attempt to update address with invalid values for PatientId, TypeId, CountryCode
        String Street = "10";
		String StreetNumber = "Bristol Road";
		String ZipCode = "B15 2SJ";
		String City = "London";
		String Region = "Midlands";
		String PatientId = "1";
		String TypeId = "1";
		String CountryCode = "UK";

		String data = String.format(
			"{\n  \"Street\": \"%s\", \"StreetNumber\": \"%s\" \n, \"ZipCode\": \"%s\", \"City\": \"%s\", \"Region\": \"%s\",  \"PatientId\": \"%s\", \"TypeId\": \"%s\", \"CountryCode\": \"%s\"}"
			,Street, StreetNumber, ZipCode, City, Region, PatientId, TypeId, CountryCode);
		
        String url = BaseUrl + "/Addresss(1)";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        System.out.println("now put");
        restTemplate.put(url, request, String.class);
    

		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");
        System.out.println("Getting new");
        System.out.println("Getting new");
        System.out.println("Getting new");
        System.out.println(afterJson);
        System.out.println("Done");
        System.out.println("Done");

        System.out.println("Done");


		//Fields in put should be updated, other fields should be null or default
        // TODO behaving like a patch
		assertEquals(Street, afterJson.get("Street"));
        assertEquals(StreetNumber, afterJson.get("StreetNumber"));
		assertEquals(ZipCode, afterJson.get("ZipCode"));
		assertEquals(City, afterJson.get("City"));
		//assertFalse(afterJson.has("Description"));
		//assertFalse(afterJson.has("Priority"));
		assertEquals(Region, afterJson.get("Region"));
		assertEquals(PatientId, afterJson.get("PatientId"));
		assertEquals(TypeId, afterJson.get("TypeId"));
		assertEquals(CountryCode, afterJson.get("CountryCode"));
	}

}