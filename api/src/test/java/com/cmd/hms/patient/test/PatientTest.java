package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
 
public class PatientTest extends IntegrationTest{
    @Test
	public void addPatient() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Charles\", \"MiddleName\": \"Francis\" \n, \"LastName\": \"Xaiver\", \"PatientStatusId\": \"1\", \"GenderId\": \"1\", \"TitleId\": \"1\", \"DateOfBirth\": \"1999-04-01T04:00:00\"}";
		String endPoint =  "/Patients";

		assertTrue(add(endPoint, requestBody, this.adminToken));
	}

	@Test
	public void minimalAddPatient() throws Exception {
		Random random = new Random();
		String FirstName = RandomStringUtils.randomAlphanumeric(random.nextInt(99) + 1);
		String LastName = RandomStringUtils.randomAlphanumeric(random.nextInt(99) + 1);
		Integer Status = random.nextInt(3) + 2;
		
		String requestBody = String.format("{\n  \"FirstName\": \"%s\", \"LastName\": \"%s\", \"PatientStatusId\": \"%s\"}",
								FirstName, LastName, Status);
		String endpoint =  "/Patients";

		assertTrue(add(endpoint, requestBody, this.adminToken));
	}
    
    @Test
	public void invalidPatientId() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"MiddleName\": \"Mathison\" \n, \"LastName\": \"Turing\", \"PatientStatusId\": \"10\", \"GenderId\": \"1\", \"TitleId\": \"1\", \"DateOfBirth\": \"1920-04-01T04:00:0\"}";
		String endpoint = "/Patients(1)";

		assertTrue(invalidUpdateObject(requestBody, endpoint, this.adminToken));
	}

	@Test
	public void invalidPatientGender() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"GenderId\": \"10\"}";
		String endpoint = "/Patients(1)";

		assertTrue(invalidUpdateObject(requestBody, endpoint, this.adminToken));
	}

	@Test
	public void invalidPatientTitle() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"TitleId\": \"10\"}";
		String endpoint = "/Patients(1)";
		
		assertTrue(invalidUpdateObject(requestBody, endpoint, this.adminToken));
	}

	@Test
	public void updatePatient() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"MiddleName\": \"Mathison\" \n, \"LastName\": \"Turing\", \"DateOfBirth\": \"1920-04-01T04:00:00\"}";
		String endpoint = "/Patients(1)";
		
		assertTrue(update(endpoint, requestBody, this.adminToken));
	}


    @Test
	public void deletePatient() throws Exception {
		String endPoint = "/Patients(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
    }

}