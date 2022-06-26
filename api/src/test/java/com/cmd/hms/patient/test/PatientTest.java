package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
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
		String requestBody = "{\n  \"FirstName\": \"Charles\", \"MiddleName\": \"Francis\" \n, \"LastName\": \"Xaiver\", \"PatientStatusId\": \"1\", \"GenderId\": \"1\", \"TitleId\": \"1\", \"DateOfBirth\": \"1999-04-01T04:00:00\"}";
		String endpoint =  "/Patients";
		addObject(requestBody, endpoint);
	}

	@Test
	public void minimalAddPatient() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Charles\", \"LastName\": \"Xaiver\"}";
		String endpoint =  "/Patients";
		addObject(requestBody, endpoint);
	}
    
    @Test
	public void invalidPatientId() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"MiddleName\": \"Mathison\" \n, \"LastName\": \"Turing\", \"PatientStatusId\": \"10\", \"GenderId\": \"1\", \"TitleId\": \"1\", \"DateOfBirth\": \"1920-04-01T04:00:0\"}";
		String endpoint = "/Patients(1)";
		invalidUpdateObject(requestBody, endpoint);
	}

	@Test
	public void invalidPatientGender() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"GenderId\": \"10\"}";
		String endpoint = "/Patients(1)";
		invalidUpdateObject(requestBody, endpoint);
	}

	@Test
	public void invalidPatientTitle() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"TitleId\": \"10\"}";
		String endpoint = "/Patients(1)";
		invalidUpdateObject(requestBody, endpoint);
	}

	@Test
	public void updatePatient() throws Exception {
		String requestBody = "{\n  \"FirstName\": \"Alan\", \"MiddleName\": \"Mathison\" \n, \"LastName\": \"Turing\", \"DateOfBirth\": \"1920-04-01T04:00:00\"}";
		String endpoint = "/Patients(1)";
		updateObject(requestBody, endpoint);
	}

    @Test
	public void deletePatient() throws Exception {
		String endpoint = "/Patients(2)";
		deleteObject(endpoint);
    }

}