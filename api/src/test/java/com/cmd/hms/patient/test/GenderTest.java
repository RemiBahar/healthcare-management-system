package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

     // Integration tests
    @Test
	public void addGender() throws Exception {
		String requestBody = "{\n  \"Title\": \"Gender\" \n}";
		String endpoint = "/Genders";
		addObject(requestBody, endpoint);
	}

    @Test
	public void updateGender() throws Exception {
		String requestBody = "{\n  \"Title\": \"New Gender\" \n}";
		String endpoint = "/Genders(1)";
		updateObject(requestBody, endpoint);
	}

    @Test
	public void invalidUpdateGender() throws Exception {
		String requestBody = "{\n \"GenderId\": \"10\", \"Title\": \"Something\" \n}";
		String endpoint = "/Genders(1)";
		invalidUpdateObject(requestBody, endpoint);
	}

    @Test
	public void deleteGender() throws Exception {
		String endpoint = "/Genders(2)";
		deleteObject(endpoint);
	}
    
}