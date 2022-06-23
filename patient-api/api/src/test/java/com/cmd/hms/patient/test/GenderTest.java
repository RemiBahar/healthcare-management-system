package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.cmd.hms.patient.model.Gender;

public class GenderTest extends HttpRequestTest{

     // Instanstiate object
     Gender gender = new Gender();

     // Tests
     @Test
     public void IdTest(){
         Long Id = 1L;
         gender.setGenderId(Id);
             
         assertTrue(gender.getGenderId().equals(Id), "Id");
     }

     @Test
     public void TitleTest(){
         String Title = "Male";
         gender.setTitle(Title);
             
         assertTrue(gender.getTitle().equals(Title), "Title");
     }

     @Test
     public void IsDeletedTest(){
         Boolean IsDeleted = false;
         gender.setIsDeleted(false);
             
         assertTrue(gender.getIsDeleted().equals(IsDeleted), "IsDeleted");
     }

    @Test
	public void addGender() throws Exception {
		// Add gender 
		String Title = "Male";
		String data = String.format("{\n  \"Title\": \"%s\" \n}", Title);
		String url = BaseUrl + "/Genders";
		
		String getUrl = postObject(data, url);

		// Get added Gender
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added gender with request
		assertEquals(Title, getJson.get("Title"));
		assertEquals(false, getJson.get("IsDeleted"));

		
	}

   
    
}