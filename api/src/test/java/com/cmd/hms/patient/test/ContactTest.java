package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContactTest extends IntegrationTest{
    @Test
	public void addContact() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"+44162822233\" \n, \"Mobile\": \"0772224455\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts";

        assertTrue(add(endpoint, requestBody, this.adminToken));
	}

    
    @Test
	public void minimalAddContact() throws Exception {
        /*
         * Update this according to what fields are mandatory according to requirements
         */
		String requestBody = "{\n  \"Name\": \"Patrick Alan Smith\", \"PatientId\": \"1\"}";
        String endpoint = "/Contacts";

        assertTrue(add(endpoint, requestBody, adminToken));
	}
    
    
    @Test
	public void invalidContactPatient() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"01628222333\" \n, \"Mobile\": \"07722244555\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"10\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts(2)";
        
        assertTrue(invalidUpdateObject(requestBody, endpoint, adminToken));
	}

    @Test
	public void invalidContactType() throws Exception {
		String requestBody = "{\n  \"Name\": \"Bobby John Jones\", \"TypeId\": \"1\"}";
        String endpoint = "/Contacts(2)";
        
        assertTrue(invalidUpdateObject(requestBody, endpoint, adminToken));
	}

    @Test
	public void deleteContact() throws Exception {
        String endPoint = "/Contacts(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
    }

    /* Field validation tests */
    /* 
    @Test
	public void missingPatient() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"0162822233\" \n, \"Mobile\": \"0772224455\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Addresss";
        httpRequestTest.invalidAddObject(requestBody, endpoint, adminToken);
    }

    @Test
	public void invalidEmail() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"0162822233\" \n, \"Mobile\": \"0772224455\", \"Email\": \"testhotmail.com\", \"Priority\": \"1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Addresss";
        httpRequestTest.invalidAddObject(requestBody, endpoint, adminToken);
    }

    @Test
	public void invalidTelephone() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"1234\" \n, \"Mobile\": \"0772224455\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Addresss";
        httpRequestTest.invalidAddObject(requestBody, endpoint, adminToken);
    }

    @Test
	public void invalidMobile() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"0162822233\" \n, \"Mobile\": \"1234\", \"Email\": \"test@hotmail.com\", \"Priority\": \"1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Addresss";
        httpRequestTest.invalidAddObject(requestBody, endpoint, adminToken);
    }

    @Test
	public void invalidPriority() throws Exception {
		String requestBody = "{\n  \"Name\": \"John Smith Wick\", \"Telephone\": \"0162822233\" \n, \"Mobile\": \"0772224455\", \"Email\": \"test@hotmail.com\", \"Priority\": \"-1\", \"PatientId\": \"1\", \"TypeId\": \"1\"}";
        String endpoint = "/Addresss";
        httpRequestTest.invalidAddObject(requestBody, endpoint, adminToken);
    }*/
}