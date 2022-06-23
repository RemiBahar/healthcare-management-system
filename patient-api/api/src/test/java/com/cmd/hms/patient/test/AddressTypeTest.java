package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class AddressTypeTest extends HttpRequestTest{
    @Test
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
    
}
