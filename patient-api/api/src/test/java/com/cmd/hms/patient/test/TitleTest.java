package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Title;

public class TitleTest extends HttpRequestTest{

     // Instanstiate object
     Title title = new Title();

     // Tests
     @Test
     public void IdTest(){
         Long Id = 1L;
         title.setTitleId(Id);
             
         assertTrue(title.getTitleId().equals(Id), "Id");
     }

     @Test
     public void TitleMethodTest(){
         String Title = "Mr";
         title.setTitle(Title);
             
         assertTrue(title.getTitle().equals(Title), "Title");
     }
    
    @Test
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
}