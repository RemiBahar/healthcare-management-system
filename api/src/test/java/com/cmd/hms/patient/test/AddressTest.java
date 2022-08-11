package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
 
public class AddressTest extends IntegrationTest{
    // Integration tests
    @Test
	public void addAddress() throws Exception {
		String requestBody = "{\n  \"Street\": \"Richmond Road\", \"StreetNumber\": \"21\" \n, \"ZipCode\": \"TF54 5PH\", \"City\": \"Telford\", \"Description\": \"My home address\", \"Priority\": \"1\", \"Region\": \"Shropshire\",  \"PatientId\": \"1\", \"TypeId\": \"1\", \"CountryCode\": \"UK\"}";
        String endPoint = "/Addresss";

        assertTrue(add(endPoint, requestBody, this.adminToken));
    }


    @Test
	public void minimalAddAddress() throws Exception {
        /*
         * Update according to minimal required fields
         */
        String requestBody = "{\n  \"Street\": \"Richmond Road\", \"StreetNumber\": \"21\" \n, \"City\": \"Telford\", \"PatientId\": \"1\", \"CountryCode\": \"UK\"}";
        String endPoint = "/Addresss";

        assertTrue(add(endPoint, requestBody, this.adminToken));
    }

    
    /* Test update */

    @Test
	public void updateAddress() throws Exception {
		String requestBody = "{\n  \"Street\": \"Dawlish Road\", \"StreetNumber\": \"1\" \n, \"ZipCode\": \"B29 7AU\", \"City\": \"Birmingham\", \"Region\": \"West Midlands\"}";
        String endPoint = "/Addresss(1)";

        assertTrue(update(endPoint, requestBody, adminToken));
	}


    @Test
	public void deleteAddress() throws Exception {
        String endPoint = "/Addresss(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
	}
}