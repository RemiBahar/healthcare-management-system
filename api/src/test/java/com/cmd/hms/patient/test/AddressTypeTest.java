package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddressTypeTest extends IntegrationTest{
	@Test
	public void addAddressType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Test\" \n}";
        String endPoint = "/AddressTypes";

        assertTrue(add(endPoint, requestBody, adminToken));
    }

    @Test
	public void updateAddressType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Something\" \n}";
        String endPoint = "/AddressTypes(1)";

        assertTrue(update(endPoint, requestBody, adminToken));
	}

    @Test
    public void invalidUpdateAddressType() throws Exception {
        String requestBody = "{\"Title\": \"\" \n}";
        String endPoint = "/AddressTypes(1)";
        assertTrue(invalidUpdateObject(endPoint, requestBody, this.adminToken));
    }

    @Test
    public void deleteAddressType() throws Exception {
        String endPoint = "/AddressTypes(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
    }

    @Test
    // Assistance should not be able to READ AddressType
    public void assistanceGetAddressType() throws Exception {
        Boolean request = invalidGet("/AddressTypes", this.assistanceToken);
        assertTrue(request);
    }

    @Test
    // User should not be able to READ AddressType
    public void userGetAddressType() throws Exception {
        Boolean request = invalidGet("/AddressTypes", this.userToken);
        assertTrue(request);
    }
    
}
