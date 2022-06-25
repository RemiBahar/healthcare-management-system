package com.cmd.hms.patient.test;

import org.junit.jupiter.api.Test;

public class AddressTypeTest extends HttpRequestTest{
	@Test
	public void addAddressType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Test\" \n}";
        String endpoint = "/AddressTypes";
        addObject(requestBody, endpoint);
    }

    @Test
	public void updateAddressType() throws Exception {
		String requestBody = "{\n  \"Title\": \"Something\" \n}";
        String endpoint = "/AddressTypes(1)";

        updateObject(requestBody, endpoint);
	}

    @Test
	public void deleteAddress() throws Exception {
        deleteObject("/AddressTypes(2)");
    }
    
}
