package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
  webEnvironment = WebEnvironment.DEFINED_PORT,
  properties = {
    "server.port=8079"
  })
@TestPropertySource(locations="classpath:application-test.properties") // Uses postgres database in memory deleted after testing
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Needed or test order doesn't match method order. Slows down test execution
public class HttpRequestTest{

	@Autowired
    protected TestRestTemplate restTemplate;
	
	String BaseUrl ="http://localhost:8079/odata";

	public String postObject(String data, String url) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		String postResponse = restTemplate.postForObject(url, request, String.class);
		JSONObject postJson = new JSONObject(postResponse).getJSONObject("d").getJSONObject("__metadata");
		return postJson.get("id").toString();
	}

    @Test
	@Order(1)
	public void addTitle() throws Exception {
		// Add title 
		String Title = "Title";
		String data = String.format("{\n  \"Title\": \"%s\" \n}", Title);
		String url = BaseUrl + "/Titles";

		String getUrl = postObject(data, url);
		
		// Get added title
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added title with request
		assertEquals(Title, getJson.get("Title"));
	}

   public String postPatientStatusType(String Status) throws Exception {
	String data = String.format("{\n  \"Status\": \"%s\" \n}", Status);
	String url = BaseUrl + "/PatientStatusTypes";
	String getUrl = postObject(data, url);

	return getUrl;

   }

    @Test
	@Order(1)
	public void addPatientStatusType() throws Exception {
		// Add object 
		String Status = "TestStatus";
		
		String getUrl = postPatientStatusType(Status);

		// Get added object
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(Status, getJson.get("Status"));	
	}


	
	@Test
	@Order(1)
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject(BaseUrl + "/$metadata",
				String.class)).contains("Schema");
	}

	
	
	

	

	@Test
	@Order(2)
	public void addPatient() throws Exception {
		// Patient status not finish being added unless you call it again here		
		
		// Add object 
		String FirstName = "Charles";
		String MiddleName = "Francis";
		String LastName = "Xaiver";
		String PatientStatusId = "1";
		String GenderId = "1";
		String TitleId = "1";
		String DateOfBirth = "1999-04-01T04:00:00";

		

		String data = String.format(
			"{\n  \"FirstName\": \"%s\", \"MiddleName\": \"%s\" \n, \"LastName\": \"%s\", \"PatientStatusId\": \"%s\", \"GenderId\": \"%s\", \"TitleId\": \"%s\", \"DateOfBirth\": \"%s\"}"
			,FirstName, MiddleName, LastName, PatientStatusId, GenderId, TitleId, DateOfBirth);
		
		String url = BaseUrl + "/Patients";

		String getUrl = postObject(data, url);
		
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(FirstName, getJson.get("FirstName"));
		assertEquals(MiddleName, getJson.get("MiddleName"));
		assertEquals(LastName, getJson.get("LastName"));
		assertEquals(PatientStatusId, getJson.get("PatientStatusId"));
		assertEquals(GenderId, getJson.get("GenderId"));
		assertEquals(TitleId, getJson.get("TitleId"));

	}

	
	

	

	@Test
	@Order(1)
	public void addAddressType() throws Exception {
		// Add object 
		String Title = "Home";
		String data = String.format("{\n  \"Title\": \"%s\" \n}", Title);
		String url = BaseUrl + "/AddressTypes";
	
		String getUrl = postObject(data, url);
		
		// Get added object
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(Title, getJson.get("Title"));	
	}



	

	
	@Test
	@Order(3)
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
		assertEquals("1", getJson.get("AddressId"));
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
	@Order(3)
	public void addContact() throws Exception {
		// Patient not finish being added unless you call it again here
		// Add object 
		String Name = "John Smith Wick";
		String Telephone = "01628222333";
		String Mobile = "07722244555";
		String Email = "test@hotmail.com";
		String Priority = "1";
		String PatientId = "1";
		String TypeId = "1";

		String data = String.format(
			"{\n  \"Name\": \"%s\", \"Telephone\": \"%s\" \n, \"Mobile\": \"%s\", \"Email\": \"%s\", \"Priority\": \"%s\", \"PatientId\": \"%s\", \"TypeId\": \"%s\"}"
			,Name, Telephone, Mobile, Email, Priority, PatientId, TypeId);
		
		String url = BaseUrl + "/Contacts";
		String getUrl = postObject(data, url);

		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals("1", getJson.get("Id"));
		assertEquals(Name, getJson.get("Name"));
		assertEquals(Telephone, getJson.get("Telephone"));
		assertEquals(Mobile, getJson.get("Mobile"));
		assertEquals(Email, getJson.get("Email"));
		assertEquals(Priority, getJson.get("Priority"));
		assertEquals(PatientId, getJson.get("PatientId"));
		assertEquals(TypeId, getJson.get("TypeId"));
	}

	@Test
	@Order(4)
	public void invalidPutPatient() throws Exception {

	
		String before = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject beforeJson = new JSONObject(before).getJSONObject("d");
		
		
		// Add object 
		String FirstName = "Alan";
		String MiddleName = "Mathison";
		String LastName = "Turing";
		String PatientStatusId = "10";
		String GenderId = "10";
		String TitleId = "10";
		String DateOfBirth = "1920-04-01T04:00:00";

		String data = String.format(
			"{\n  \"FirstName\": \"%s\", \"MiddleName\": \"%s\" \n, \"LastName\": \"%s\", \"PatientStatusId\": \"%s\", \"GenderId\": \"%s\", \"TitleId\": \"%s\", \"DateOfBirth\": \"%s\"}"
			,FirstName, MiddleName, LastName, PatientStatusId, GenderId, TitleId, DateOfBirth);
		
		String url = BaseUrl + "/Patients(1)";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		restTemplate.put(url, request, String.class);

		String after = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject afterJson = new JSONObject(after).getJSONObject("d");

		// Compare added object with request
		assertEquals(beforeJson.get("PatientId"), afterJson.get("PatientId"));
		assertEquals(beforeJson.get("FirstName"), afterJson.get("FirstName"));
		assertEquals(beforeJson.get("MiddleName"), afterJson.get("MiddleName"));		
		assertEquals(beforeJson.get("LastName"), afterJson.get("LastName"));		
		assertEquals(beforeJson.get("PatientStatusId"), afterJson.get("PatientStatusId"));		
		assertEquals(beforeJson.get("GenderId"), afterJson.get("GenderId"));		
		assertEquals(beforeJson.get("TitleId"), afterJson.get("TitleId"));		

	}

	@Test
	@Order(5)
	public void patchPatient() throws Exception {
	
	
		String before = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject beforeJson = new JSONObject(before).getJSONObject("d");
		
		
		// Add object 
		String FirstName = "Alan";
		String MiddleName = "Mathison";
		String LastName = "Turing";
		String DateOfBirth = "1920-04-01T04:00:00";

		String data = String.format(
			"{\n  \"FirstName\": \"%s\", \"MiddleName\": \"%s\" \n, \"LastName\": \"%s\", \"DateOfBirth\": \"%s\"}"
			,FirstName, MiddleName, LastName, DateOfBirth);
		
		String url = BaseUrl + "/Patients(1)";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		restTemplate.patchForObject(url, request, String.class);

		String after = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject afterJson = new JSONObject(after).getJSONObject("d");

		// Compare added object with request
		assertEquals(beforeJson.get("PatientId"), afterJson.get("PatientId"));
		assertEquals(FirstName, afterJson.get("FirstName"));
		assertEquals(MiddleName, afterJson.get("MiddleName"));		
		assertEquals(LastName, afterJson.get("LastName"));		
		assertEquals(beforeJson.get("PatientStatusId"), afterJson.get("PatientStatusId"));		
		assertEquals(beforeJson.get("GenderId"), afterJson.get("GenderId"));		
		assertEquals(beforeJson.get("TitleId"), afterJson.get("TitleId"));		
		assertNotEquals(beforeJson.get("DateOfBirth"), afterJson.get("DateOfBirth"));		

	}


	@Test
	@Order(6)
	public void putPatient() throws Exception {
	
		// Add object 
		String FirstName = "Charles";
		String MiddleName = "Francis";
		String LastName = "Xaiver";
		String PatientStatusId = "1";
		String GenderId = "1";
		String TitleId = "1";
		String DateOfBirth = "1999-04-01T04:00:00";

		String data = String.format(
			"{\n  \"FirstName\": \"%s\", \"MiddleName\": \"%s\" \n, \"LastName\": \"%s\", \"PatientStatusId\": \"%s\", \"GenderId\": \"%s\", \"TitleId\": \"%s\", \"DateOfBirth\": \"%s\"}"
			,FirstName, MiddleName, LastName, PatientStatusId, GenderId, TitleId, DateOfBirth);
		
		String url = BaseUrl + "/Patients(1)";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		restTemplate.put(url, request, String.class);

		String getResponse = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		System.out.println("put" + getJson);

		// Compare added object with request
		assertEquals("1", getJson.get("PatientId"));
		assertEquals(FirstName, getJson.get("FirstName"));
		assertEquals(MiddleName, getJson.get("MiddleName"));
		assertEquals(LastName, getJson.get("LastName"));
		assertEquals(PatientStatusId, getJson.get("PatientStatusId"));
		assertEquals(GenderId, getJson.get("GenderId"));
		assertEquals(TitleId, getJson.get("TitleId"));

	}


    @Test
	@Order(7)
	public void deletePatient() throws Exception {

		// Add object 
		postPatientStatusType("Pre-checked");
		postPatientStatusType("Checked");
		postPatientStatusType("In-treatment");
		postPatientStatusType("For-deletion");

		// Delete patient
		String url = BaseUrl + "/Patients(1)";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate.delete(url, String.class);

		//Check if patient deleted
		String getResponse = restTemplate.getForObject(BaseUrl + "/Patients(1)",String.class);
		JSONObject getJson = new JSONObject(getResponse);

		assertTrue(getJson.has("error"));


    }

	@Test
	@Order(1)
	public void addGender() throws Exception {
		// Add gender 
		String Title = "Male";
		String data = String.format("{\n  \"Title\": \"%s\" \n}", Title);
		String url = BaseUrl + "/Genders";
		
		String getUrl = postObject(data, url);

		// Get added Gender
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added gender with request
		assertEquals(Title, getJson.get("Title"));
		assertEquals(false, getJson.get("IsDeleted"));

		
	}

	@Test
	@Order(1)
	public void addCountry() throws Exception {
		// Add object 
		String CountryCode = "DE";
		String Name = "Germany";

		String data = String.format("{\n  \"CountryCode\": \"%s\",  \"Name\": \"%s\" \n}", CountryCode, Name);
		String url = BaseUrl + "/Countrys";
		
		String getUrl = postObject(data, url);
	
		// Get added object
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added object with request
		assertEquals(CountryCode, getJson.get("CountryCode"));
		assertEquals(Name, getJson.get("Name"));
	}

	@Test
	@Order(1)
	public void addContactType() throws Exception {
		// Add contact type 
		String Title = "Family";
		String data = String.format("{\n  \"Title\": \"%s\" \n}", Title);
		String url = BaseUrl + "/ContactTypes";

		String getUrl = postObject(data, url);
	
		// Get added contact type
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added contact type with request
		assertEquals(Title, getJson.get("Title"));
	}
	
}