package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cmd.hms.patient.model.Patient;
 
public class PatientTest extends HttpRequestTest{
 

    // Create empty object
    Patient Patient = new Patient();

    // Tests
    @org.junit.jupiter.api.Test
    public void PatientIdTest(){
        Long PatientId = 1L;
        Patient.setPatientId(PatientId);

        assertTrue(Patient.getPatientId().equals(PatientId), "PatientId");
    }

    @org.junit.jupiter.api.Test
    public void PatientFirstNameTest(){
        String FirstName = "Jon";
        Patient.setFirstName(FirstName);

        assertTrue(Patient.getFirstName().equals(FirstName), "FirstName");
    }

    @org.junit.jupiter.api.Test
    public void PatientMiddleNameTest(){
        String MiddleName = "Edward";
        Patient.setMiddleName(MiddleName);

        assertTrue(Patient.getMiddleName().equals(MiddleName), "MiddleName");
    }

    @org.junit.jupiter.api.Test
    public void PatientLastNameTest(){
        String LastName = "Smith";
        Patient.setLastName(LastName);

        assertTrue(Patient.getLastName().equals(LastName), "LastName");
    }

    @org.junit.jupiter.api.Test
    public void PatientGenderIdTest(){
        Long GenderId = 1L;
        Patient.setGenderId(GenderId);
            
        assertTrue(Patient.getGenderId().equals(GenderId), "GenderId");
    }

    @org.junit.jupiter.api.Test
    public void PatientTitleIdTest(){
        Long TitleId = 1L;
        Patient.setTitleId(TitleId);

        assertTrue(Patient.getTitleId().equals(TitleId), "TitleId");
    }

    @org.junit.jupiter.api.Test
    public void PatientDateOfBirthTest(){
        Date DateOfBirth = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        formatter.format(DateOfBirth);

        Patient.setDateOfBirth(DateOfBirth);

        assertTrue(Patient.getDateOfBirth().equals(DateOfBirth), "DateOfBirth");
    }


    @Test
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
	public void deletePatient() throws Exception {
		// Delete patient
		String url = BaseUrl + "/Patients(2)";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate.delete(url, String.class);

		//Check if patient deleted
		String getResponse = restTemplate.getForObject(BaseUrl + "/Patients(2)",String.class);
		JSONObject getJson = new JSONObject(getResponse);

		assertTrue(getJson.has("error"));


    }

   
}