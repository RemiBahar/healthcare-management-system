package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.ContactType;

public class ContactTypeTest extends HttpRequestTest{

     // Instanstiate object
     ContactType contactType = new ContactType();

     // Tests
     @Test
     public void ContactTypeIdTest(){
         Long TypeId = 1L;
         contactType.setContactTypeId(TypeId);
             
         assertTrue(contactType.getContactTypeId().equals(TypeId), "TypeId");
     }

     @Test
     public void TitleTest(){
         String Title = "Family";
         contactType.setTitle(Title);
             
         assertTrue(contactType.getTitle().equals(Title), "Title");
     }

    @Test
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