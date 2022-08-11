package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.cmd.hms.patient.model.Gender;

public class GenderTest extends IntegrationTest{
    // Unit tests
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


    // Integration testing
    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void addGender(){
        // Prepare request
        String endPoint = "/Genders";
        String requestBody = "{\n  \"Title\": \"Gender\" \n}";

        // Perform add object test
        Boolean result = add(endPoint, requestBody, this.adminToken);

        // Check if true
        assertTrue(result);
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void updateGender(){
        String endPoint = "/Genders(1)";
        String requestBody = "{\n  \"Title\": \"Gender\" \n}";

        Boolean result = update(endPoint, requestBody, this.adminToken);
        assertTrue(result);
    }

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
	public void invalidUpdateGender() throws Exception {
		String requestBody = "{\n \"GenderId\": \"10\", \"Title\": \"Something\" \n}";
		String endpoint = "/Genders(1)";
		Boolean result = invalidUpdateObject(requestBody, endpoint, adminToken);

        assertTrue(result);
	}

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
	public void deleteGender() throws Exception {
        String endPoint = "/Genders(3)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
	}

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    // Assistance should not be able to READ Gender
	public void assistanceGetGender() throws Exception {
        Boolean request = invalidGet("/Genders", this.assistanceToken);
        assertTrue(request);
	}

    @Test
    @Order(Ordered.LOWEST_PRECEDENCE)
    // User should not be able to READ Gender
	public void userGetGender() throws Exception {
        Boolean request = invalidGet("/Genders", this.userToken);
        assertTrue(request);
	}
    
}
